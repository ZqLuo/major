package com.example.dao;

import com.example.entity.SysCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * Created by zqLuo
 */
public interface SysCodeRepository extends JpaRepository<SysCode,Integer>,JpaSpecificationExecutor<SysCode> {
    /**
     * 根据类型和编码获取系统编码
     * @param type
     * @param code
     * @return
     */
    @Query("select o from SysCode o where o.type = ?1 and o.code = ?2")
    SysCode findByTypeAndCode(String type,String code);

    /**
     * 根据类型获取系统编码
     * @param type
     * @return
     */
    @Query("select o from SysCode o where o.type = ?1 order by o.ora")
    List<SysCode> findSysCodesByType(String type);
}
