package com.example.dao;

import com.example.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zqLuo
 */
public interface SysUserRepository extends JpaRepository<SysUser,String>{

    @Query("select user from SysUser user where username = ?1")
    SysUser findUserByUsername(String userName);


}
