package com.example.entity;

import javax.persistence.*;

/**
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_user_menu")
public class SysUserMenu {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "menu_id")
    private Integer menuId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getOra() {
        return ora;
    }

    public void setOra(Integer ora) {
        this.ora = ora;
    }
}
