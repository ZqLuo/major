package com.example.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_user")
public class SysUser{

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 用户名即登录名
     */
    @Column(length = 32)
    private String username;
    /**
     * 真实姓名
     */
    @Column(name = "true_name",length = 32)
    private String trueName;
    /**
     * 邮箱
     */
    @Column(name="email",length = 100)
    private String email;
    /**
     * 手机
     */
    @Column(name = "tel_name",length = 11)
    private Integer telPhone;
    /**
     * 密码
     */
    @Column(length = 255)
    private String password;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(Integer telPhone) {
        this.telPhone = telPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
