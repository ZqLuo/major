package com.example.service;

import com.example.entity.SysUser;

/**
 * Created by zqLuo
 */
public interface SysUserService {
    SysUser findUserByUsername(String userName);

    void create(SysUser sysUser);
}
