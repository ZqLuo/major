package com.example.controller;

import com.example.entity.SysMenu;
import com.example.entity.SysUser;
import com.example.service.SysMenuService;
import com.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqLuo
 */
@Controller
@RequestMapping("sysMenuController")
public class SysMenuController extends BaseController{

    @Autowired
    private SysMenuService sysMenuService;

    @ResponseBody
    @RequestMapping(value = "getMenuListShow")
    public Map<String,Object> getMenuListShow(){
        Map<String,Object> map = new HashMap<String,Object>();
        SysUser sysUser = findLoginUser();
        if(sysUser != null && StringUtil.isNotEmpty(sysUser.getId())){
            map.put("menuList", sysMenuService.getMenusByUser(findLoginUser()));
            map.put("username",sysUser.getTrueName());
        }
        return map;
    }

}
