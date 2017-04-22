package com.example.controller;

import com.example.entity.SecurityUser;
import com.example.entity.SysUser;
import com.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zqLuo
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    public SysUser findLoginUser(){
        SecurityContextImpl securityContextImpl = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysUser sysUser =  (SysUser)securityContextImpl.getAuthentication().getPrincipal();
        return sysUser;
    }

    public int getPage(){
        String page = request.getParameter("page");
        if(StringUtil.isNotEmpty(page)){
            return Integer.parseInt(page);
        }
        return 1;
    }

    public int getSize(){
        String size = request.getParameter("size");
        if(StringUtil.isNotEmpty(size)){
            return Integer.parseInt(size);
        }
        return 10;
    }
}
