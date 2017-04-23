package com.example.controller;

import com.example.constant.SysCodeEnum;
import com.example.constant.SysConstant;
import com.example.entity.SecurityUser;
import com.example.entity.SysCode;
import com.example.entity.SysUser;
import com.example.service.SysCodeService;
import com.example.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zqLuo
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysCodeService sysCodeService;

    public SysUser findLoginUser(){
//        SecurityContextImpl securityContextImpl = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//        SysUser sysUser =  (SysUser)securityContextImpl.getAuthentication().getPrincipal();
        SysUser sysUser = (SysUser)request.getSession().getAttribute(SysConstant.LOGIN_USER);
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

    /**
     * 根据类型获取系统编码
     * @param sysCodeEnum
     * @return
     */
    public List<SysCode> findSysCodesByType(SysCodeEnum sysCodeEnum){
        return sysCodeService.findSysCodesByType(sysCodeEnum.getType());
    }
}
