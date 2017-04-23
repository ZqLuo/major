package com.example.util;

import com.example.entity.SysUser;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取上下文环境
 * Created by zqLuo
 */
@Component
public class AppUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext; // Spring应用上下文环境

    /**
     * 实现了ApplicationContextAware 接口，必须实现该方法；
     * 通过传递applicationContext参数初始化成员变量applicationContext
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class clazz) throws BeansException {
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * 获取登录用户
     * @return
     */
    public static SysUser getLoninUser(){
        ServletRequestAttributes rs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest  request = rs.getRequest();
        SecurityContextImpl securityContextImpl = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysUser sysUser =  (SysUser)securityContextImpl.getAuthentication().getPrincipal();
        return sysUser;
    }
}
