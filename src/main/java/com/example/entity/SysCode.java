package com.example.entity;

import javax.persistence.*;

/**
 * 系统编码
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_code")
public class SysCode {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 名称
     */
    @Column(name = "name",length = 32,nullable = false)
    private String name;
    /**
     * 类型 SysCodeEnum
     */
    @Column(name = "type",length = 32,nullable = false)
    private String type;
    /**
     * 编码
     */
    @Column(name = "code",length = 32,nullable = false)
    private String code;
    /**
     * 排序
     */
    private Integer ora;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOra() {
        return ora;
    }

    public void setOra(Integer ora) {
        this.ora = ora;
    }
}
