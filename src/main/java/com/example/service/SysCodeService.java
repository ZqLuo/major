package com.example.service;

import com.example.entity.SysCode;
import com.example.util.PageReturn;
import com.example.vo.SysCodeQueryVo;
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

    /**
     * 分页获取系统编码
     * @param sysCodeQueryVo
     * @param page
     * @param size
     * @return
     */
    PageReturn getSysCodeList(SysCodeQueryVo sysCodeQueryVo, int page, int size);

    /**
     * 根据id获取系统编码
     * @param id
     * @return
     */
    SysCode getSysCodeById(String id);

    /**
     * 保存或修改系统编码
     * @param sysCode
     */
    void saveOrUpdateSysCode(SysCode sysCode) throws Exception;

    /**
     * 删除系统编码
     * @param sysCode
     */
    void delSysCodeById(SysCode sysCode);
}
