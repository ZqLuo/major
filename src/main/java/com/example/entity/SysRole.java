package com.example.entity;

import com.example.util.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_role")
public class SysRole extends IdEntity{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
