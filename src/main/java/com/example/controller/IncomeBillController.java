package com.example.controller;

import com.example.constant.AjaxJson;
import com.example.constant.MenuTypeEnum;
import com.example.entity.IncomeBill;
import com.example.entity.SysMenu;
import com.example.service.IncomeBillService;
import com.example.service.SysMenuService;
import com.example.util.DateUtils;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by zqLuo
 */
@Controller
@RequestMapping("incomeBillController")
public class IncomeBillController extends BaseController{

    @Autowired
    private IncomeBillService incomeBillService;
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("incomeBillListPage")
    public String incomeBillListPage(Model model){
        return "incomeBill/incomeBillList";
    }

    /**
     * 获取销售订单列表
     * @param marketQueryVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "incomeBillList")
    public PageReturn marketList(IncomeBill incomeBill){
        PageReturn pageReturn = incomeBillService.getIncomeBillList(incomeBill,getPage(),getSize());
        return pageReturn;
    }

    /**
     * 进入收入编辑页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "editIncomeBillPage")
    public String editIncomeBillPage(String id,Model model){
        IncomeBill incomeBill = null;
        SysMenu sysMenu = sysMenuService.getSysMenuByType(MenuTypeEnum.INCOME_BILL_MENU.getMenyType());
        if(StringUtil.isNotEmpty(id)){
            incomeBill = incomeBillService.getEntityById(id);
            if(StringUtil.isNotEmpty(incomeBill.getCreateTime())){
                incomeBill.setCreateTimeStr(DateUtils.formatDate(incomeBill.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            }
        }else{
            incomeBill = new IncomeBill();
        }
        model.addAttribute("incomeBill",incomeBill);
        model.addAttribute("preMenuUrl",sysMenu.getMenuUrl());
        return "incomeBill/editIncomeBill";
    }

    /**
     * 编辑收入
     * @param market
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "editIncomeBill")
    public AjaxJson editIncomeBill(IncomeBill incomeBill){
        AjaxJson ajaxJson = new AjaxJson();
        incomeBillService.insert(incomeBill);
        ajaxJson.setMsg(incomeBill.getId()+"");
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "delIncomeBill")
    public AjaxJson delIncomeBill(String id){
        AjaxJson ajaxJson = new AjaxJson();
        incomeBillService.delele(id);
        return ajaxJson;
    }

}
