package com.example.service;

import com.example.entity.SysMenu;

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
}
