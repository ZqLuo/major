package com.example.controller;

import com.example.constant.AjaxJson;
import com.example.constant.MenuTypeEnum;
import com.example.constant.SysCodeEnum;
import com.example.entity.Market;
import com.example.entity.MarketDetail;
import com.example.entity.SysCode;
import com.example.entity.SysMenu;
import com.example.service.MarketService;
import com.example.service.SysMenuService;
import com.example.util.DateUtils;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.MarketQueryVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("marketListPage")
    public String marketListPage(Model model){
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

}
