package com.example.service.impl;

import com.example.dao.SysUserRepository;
import com.example.entity.SysUser;
import com.example.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by zqLuo
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysUser findUserByUsername(String userName) {
        return sysUserRepository.findUserByUsername(userName);
    }

    @Override
    public void create(SysUser sysUser) {
        sysUserRepository.save(sysUser);
    }
}
