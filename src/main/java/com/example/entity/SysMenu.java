package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 系统菜单
 * Created by zqLuo
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 菜单名称
     */
    @Column(name = "menu_name",length = 32)
    private String menuName;
    /**
     * 菜单链接
     */
    @Column(name = "menu_url",length = 100)
    private String menuUrl;
    /**
     * 上级id
     */
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private boolean isEnable;
    /**
     * 菜单等级
     */
    @Column(name = "level")
    private Integer level;
    /**
     * 下级菜单
     */
    @Transient //非数据库字段
    List<SysMenu> childMenu;
    @Column(name = "menu_type",length = 32)
    private String menuType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<SysMenu> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<SysMenu> childMenu) {
        this.childMenu = childMenu;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}
