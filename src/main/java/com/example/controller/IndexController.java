package com.example.controller;

import com.example.entity.SysUser;
import com.example.service.SysUserService;
import com.example.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zqLuo
 */
@Controller
public class IndexController {

    private final Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping("/home")
    public String home() {
        return "home";

    }


//    @PreAuthorize("hasRole('ADMIN')") 权限控制
    @RequestMapping(value = "/admin",method = RequestMethod.POST)
    public String toAdmin(){
        return "helloAdmin";
    }

    @RequestMapping("/hello")
    public String hello() {

        return "hello";

    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String root() {
        return "index";

    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
