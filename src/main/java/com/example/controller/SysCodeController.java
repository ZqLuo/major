package com.example.controller;

import com.example.constant.AjaxJson;
import com.example.constant.MenuTypeEnum;
import com.example.constant.SysCodeEnum;
import com.example.entity.SysCode;
import com.example.entity.SysMenu;
import com.example.service.SysMenuService;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.SysCodeQueryVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统编码controller
 * Created by zqLuo
 */
@Controller
@RequestMapping("sysCodeController")
public class SysCodeController extends BaseController{

    private Logger logger = org.apache.log4j.Logger.getLogger(SysCodeController.class);

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 进入系统编码管理页面
     * @param model
     * @return
     */
    @RequestMapping("sysCodeListPage")
    public String sysCodeListPage(Model model){
        List<SysCode> sysCodeTypes = SysCodeEnum.getAllSysCodeType();
        model.addAttribute("sysCodeTypes",sysCodeTypes);
        model.addAttribute("menuType", MenuTypeEnum.SYSCODE_MENU.getMenyType());
        return "sysCode/sysCodeList";
    }

    /**
     * 获取系统编码列表
     * @param productQueryVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sysCodeList",method = RequestMethod.POST)
    public PageReturn sysCodeList(SysCodeQueryVo sysCodeQueryVo){
        PageReturn pageReturn = sysCodeService.getSysCodeList(sysCodeQueryVo,getPage(),getSize());
        return pageReturn;
    }

    @RequestMapping(value = "editSysCodePage")
    public String editSysCodePage(String id,String type,Model model){
        SysCode sysCode = new SysCode();
        if(StringUtil.isNotEmpty(id)){
            sysCode = sysCodeService.getSysCodeById(id);
        }else{
            sysCode.setType(type);
        }
        model.addAttribute("sysCode",sysCode);
        SysMenu sysMenu = sysMenuService.getSysMenuByType(MenuTypeEnum.SYSCODE_MENU.getMenyType());
        model.addAttribute("preMenuUrl",sysMenu.getMenuUrl());
        return "sysCode/editSysCode";
    }

    @ResponseBody
    @RequestMapping(value = "saveSysCode")
    public AjaxJson saveSysCode(SysCode sysCode){
        AjaxJson aj = new AjaxJson();
        try {
            sysCodeService.saveOrUpdateSysCode(sysCode);
        } catch (Exception e){
            logger.error("系统编码保存失败",e);
            aj.setSuccess(false);
            aj.setMsg(e.getMessage());
        }
        return aj;
    }

    @ResponseBody
    @RequestMapping(value = "delSysCode")
    public AjaxJson delSysCode(String id){
        AjaxJson aj = new AjaxJson();
        try {
            SysCode sysCode = sysCodeService.getSysCodeById(id);
            sysCodeService.delSysCodeById(sysCode);
        } catch (Exception e){
            logger.error("系统编码保存失败",e);
            aj.setSuccess(false);
            aj.setMsg("删除失败");
        }
        return aj;
    }
}
