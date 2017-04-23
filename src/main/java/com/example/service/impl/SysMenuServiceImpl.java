package com.example.service.impl;

import com.example.dao.SysMenuRepository;
import com.example.entity.SysMenu;
import com.example.entity.SysUser;
import com.example.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zqLuo
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;

    @Override
    public List<SysMenu> getEnableSysMenus() {
        return sysMenuRepository.getSysMenu(true);
    }

    @Override
    public List<SysMenu> getMenusByUser(SysUser sysUser) {
        return sysMenuRepository.getMenusByUser(sysUser.getId());
    }
}
