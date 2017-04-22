package com.example.entity;

import javax.persistence.*;

/**
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_role")
public class SysRole{
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name",length = 32)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
