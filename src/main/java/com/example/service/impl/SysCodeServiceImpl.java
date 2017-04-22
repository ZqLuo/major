package com.example.service.impl;

import com.example.dao.SysCodeRepository;
import com.example.entity.SysCode;
import com.example.service.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zqLuo
 */
@Service
public class SysCodeServiceImpl implements SysCodeService{

    @Autowired
    private SysCodeRepository sysCodeRepository;

    @Override
    public SysCode findByTypeAndCode(String type, String code) {
        return sysCodeRepository.findByTypeAndCode(type,code);
    }

    @Override
    public List<SysCode> findSysCodesByType(String type) {
        return sysCodeRepository.findSysCodesByType(type);
    }
}
