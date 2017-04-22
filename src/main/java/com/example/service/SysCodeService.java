package com.example.service;

import com.example.entity.SysCode;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 系统编码service
 * Created by zqLuo
 */
public interface SysCodeService {

    /**
     * 根据类型和编码获取系统编码
     * @param type
     * @param code
     * @return
     */
    SysCode findByTypeAndCode(String type, String code);

    /**
     * 根据类型获取系统编码
     * @param type
     * @return
     */
    List<SysCode> findSysCodesByType(String type);
}
