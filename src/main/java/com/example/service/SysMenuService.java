package com.example.service;

import com.example.entity.SysMenu;
import com.example.entity.SysUser;

import java.util.List;

/**
 * Created by zqLuo
 */
public interface SysMenuService {
    /**
     * 获取已启用的菜单
     * @return
     */
    List<SysMenu> getEnableSysMenus();

    /**
     * 根据登录用户获取菜单
     * @param sysUser
     * @return
     */
    List<SysMenu> getMenusByUser(SysUser sysUser);

    /**
     * 根据菜单类型获取菜单
     * @param menyType
     * @return
     */
    SysMenu getSysMenuByType(String menyType);

    /**
     * 清除用户的菜单缓存
     * @param sysUser
     */
    void remvoeCacheByUser(SysUser sysUser);
}
