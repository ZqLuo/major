package com.example.service.impl;

import com.example.dao.SysCodeRepository;
import com.example.entity.SysCode;
import com.example.service.SysCodeService;
import com.example.util.JdbcUtil;
import com.example.util.PageReturn;
import com.example.vo.SysCodeQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class SysCodeServiceImpl implements SysCodeService{

    @Autowired
    private SysCodeRepository sysCodeRepository;
    @Autowired
    private JdbcUtil<SysCode> jdbcUtil;

    @Override
    public SysCode findByTypeAndCode(String type, String code) {
        return sysCodeRepository.findByTypeAndCode(type,code);
    }

    @Override
    @Cacheable(value = "sysCode",key = "#type") //将用户的菜单存入缓存
    public List<SysCode> findSysCodesByType(String type) {
        return sysCodeRepository.findSysCodesByType(type);
    }

    @Override
    public PageReturn getSysCodeList(SysCodeQueryVo sysCodeQueryVo, int page, int size) {
        String sql = "select id,code,name,ora from sys_code where type = ?";
        PageReturn pageReturn = jdbcUtil.queryForPage(sql,page,size,SysCode.class,sysCodeQueryVo.getSysCodeType());
        return pageReturn;
    }

    @Override
    public SysCode getSysCodeById(String id) {
        return sysCodeRepository.findOne(Integer.parseInt(id));
    }

    @Override
    @CacheEvict(value = "sysCode",key = "#sysCode.type")
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveOrUpdateSysCode(SysCode sysCode) throws Exception {
        SysCode oldSysCode = this.findByTypeAndCode(sysCode.getType(),sysCode.getCode());
        if(oldSysCode != null && !oldSysCode.getId().equals(sysCode.getId())){
            throw new Exception("当前编码类型下已存在相同编码");
        }
        sysCodeRepository.save(sysCode);
    }

    @Override
    @CacheEvict(value = "sysCode",key = "#sysCode.type")
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void delSysCodeById(SysCode sysCode) {
        sysCodeRepository.delete(sysCode);
    }
}
