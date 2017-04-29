package com.example.controller;

import com.example.service.SysCodeService;
import com.example.service.SysMenuService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zqLuo
 */
@Controller
public class IndexController extends BaseController{

    private final Logger logger = Logger.getLogger(IndexController.class);
    @Autowired
    private SysMenuService sysMenuService;
//    @PreAuthorize("hasRole('ADMIN')") 权限控制
//    @RequestMapping(value = "/admin",method = RequestMethod.POST)
//    public String toAdmin(){
//        return "helloAdmin";
//    }

    @RequestMapping("/")
    public String root(Model model, HttpServletRequest request) {
        //获取当前登录用户菜单
//        Set<SysMenu> sysMenus = findLoginUser().getMenus();
//        model.addAttribute("sysMenus",sysMenus);
        return "index";
    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        sysMenuService.remvoeCacheByUser(findLoginUser());
//        return "login";
//    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }

    /**
     * 首页内容
     * @return
     */
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
