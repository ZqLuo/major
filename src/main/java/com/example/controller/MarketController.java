package com.example.controller;

import com.example.constant.AjaxJson;
import com.example.constant.MenuTypeEnum;
import com.example.constant.SysCodeEnum;
import com.example.entity.*;
import com.example.service.MarketFileService;
import com.example.service.MarketService;
import com.example.service.SysMenuService;
import com.example.util.DateUtils;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.MarketQueryVo;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zqLuo
 */
@Controller
@RequestMapping("marketController")
public class MarketController extends BaseController {

    private Logger logger = Logger.getLogger(MarketController.class);

    @Autowired
    private MarketService marketService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private MarketFileService marketFileService;

    @RequestMapping("marketListPage")
    public String marketListPage(Model model){
        List<SysCode> productTypes = findSysCodesByType(SysCodeEnum.PRODUCT_TYPE);
        model.addAttribute("productTypes",productTypes);
        return "market/marketList";
    }

    /**
     * 获取销售订单列表
     * @param marketQueryVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "marketList")
    public PageReturn marketList(MarketQueryVo marketQueryVo){
        PageReturn pageReturn = marketService.getMarketList(marketQueryVo,getPage(),getSize());
        return pageReturn;
    }

    /**
     * 进入销售订单编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "editMarketPage")
    public String editMarketPage(String id,Model model){
        Market market = null;
        SysMenu sysMenu = sysMenuService.getSysMenuByType(MenuTypeEnum.MARKET_MENU.getMenyType());
        if(StringUtil.isNotEmpty(id)){
            market = marketService.getMarketById(id);
            if(StringUtil.isNotEmpty(market.getMarketDate())){
                market.setMarketDateStr(DateUtils.formatDate(market.getMarketDate(),"yyyy-MM-dd HH:mm:ss"));
            }
        }else{
            market = new Market();
            market.setMarketNo(DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
        }
        model.addAttribute("market",market);
        model.addAttribute("preMenuUrl",sysMenu.getMenuUrl());
        return "market/editMarket";
    }

    /**
     * 编辑销售订单
     * @param market
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editMarket")
    public AjaxJson editMarket(Market market){
        AjaxJson ajaxJson = new AjaxJson();
        marketService.saveOrUpdateMarket(market);
        ajaxJson.setMsg(market.getId()+"");
        return ajaxJson;
    }

    /**
     * 删除销售订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delMarket")
    public AjaxJson delMarket(String id){
        AjaxJson ajaxJson = new AjaxJson();
        marketService.delMarket(id);
        return ajaxJson;
    }

    /**
     * 进入销售详细编辑页面
     * @param marketId
     * @return
     */
    @RequestMapping(value = "goMarketDetailPage")
    public String goMarketDetailPage(String marketId,Model model){
        SysMenu sysMenu = sysMenuService.getSysMenuByType(MenuTypeEnum.MARKET_MENU.getMenyType());
        List<SysCode> productCodes = findSysCodesByType(SysCodeEnum.PRODUCT_TYPE);
        model.addAttribute("productCodes",productCodes);
        model.addAttribute("preMenuUrl",sysMenu.getMenuUrl());
        List<MarketDetail> marketDetailList = marketService.getMarketDetailListByMarketId(marketId);
        model.addAttribute("marketDetailList",marketDetailList);
        String marketEditUrl = "/marketController/editMarketPage?id=" + marketId;
        model.addAttribute("marketEditUrl",marketEditUrl);
        model.addAttribute("marketId",marketId);
        return "market/editMarketDetail";
    }

    /**
     * 删除销售详细
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delMarketDeatil")
    public AjaxJson delMarketDeatil(String id){
        AjaxJson ajaxJson = new AjaxJson();
        marketService.delMarketDeatil(id);
        return ajaxJson;
    }

    /**
     * 编辑订单详细
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editMarketDeatil")
    public AjaxJson editMarketDeatil(MarketDetail marketDetail){
        AjaxJson ajaxJson = new AjaxJson();
        try {
            marketService.saveOrUpdateMarketDeatil(marketDetail);
            ajaxJson.setMsg("保存成功");
            ajaxJson.setObj(marketDetail);
        } catch (Exception e){
            logger.error("保存商品销售详细失败",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("保存失败");
        }
        return ajaxJson;
    }

    /**
     * 销售订单详细
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "viewMarket")
    public AjaxJson viewMarket(String marketId){
        AjaxJson ajaxJson = new AjaxJson();
        Market market = marketService.getMarketById(marketId);
        List<MarketDetail> marketDetailList = marketService.getMarketDetailListByMarketId(marketId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("market",market);
        map.put("marketDetailList",marketDetailList == null ? new ArrayList<MarketDetail>() : marketDetailList);
        ajaxJson.setAttributes(map);
        return ajaxJson;
    }

    /**
     * 销售订单文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "viewMarketFile")
    public AjaxJson viewMarketFile(String marketId){
        AjaxJson ajaxJson = new AjaxJson();
        List<MarketFile> marketFiles = marketFileService.getMarketFilesByMarketId(marketId);
        ajaxJson.setObj(marketFiles);
        return ajaxJson;
    }

    /**
     * 上传文件
     * @param file
     * @param marketId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "uploadMarketFile")
    public AjaxJson uploadMarketFile(@RequestParam(value = "file", required = false) MultipartFile file,String marketId) {
        AjaxJson ajaxJson = new AjaxJson();
        try {
            MarketFile marketFile = marketFileService.uploadMarketFile(file,marketId);
            ajaxJson.setObj(marketFile);
        } catch (IOException e) {
            logger.error("上传销售文件失败",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("文件IO异常");
        }
        return ajaxJson;
    }

    /**
     * 下载销售文件
     * @param fileId
     */
    @RequestMapping(value = "downLoadMarkerFile")
    public void downLoadMarkerFile(String fileId, HttpServletResponse response){
        FileInputStream fin = null;
        InputStream in = null;
        OutputStream os = null;
        try {
            MarketFile marketFile = marketFileService.getMarketFileById(fileId);
            File file = new File(marketFile.getFilePath());
           // response.setContentType("application/force-download");// 设置强制下载不打开
//            String fileName = URLEncoder.encode(marketFile.getFileName(), "UTF-8");
            String fileName = marketFile.getFileName();
            // 清空response
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"iso-8859-1") + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            if(file.exists()){
                fin = new FileInputStream(file);
                os = response.getOutputStream();
                int count = 0;
                byte[] buffer = new byte[1024 * 1024];
                while ((count = fin.read(buffer)) != -1){
                    os.write(buffer, 0, count);
                }
                os.flush();
            }
        } catch (Exception e){
            logger.error("导出销售文件失败【" + fileId + "】",e);
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(fin != null){
                try {
                    fin.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除销售文件
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteMarketFile")
    public AjaxJson deleteMarketFile(String fileId){
        AjaxJson ajaxJson = new AjaxJson();
        marketFileService.deleteMarketFileById(fileId);
        return ajaxJson;
    }
}
