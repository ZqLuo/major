package com.example.dao;

import com.example.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zqLuo
 */
public interface SysMenuRepository extends JpaRepository<SysMenu,Integer>,JpaSpecificationExecutor<SysMenu> {

    /**
     * 获取所有菜单
     * @param b
     * @return
     */
    @Query("select m from SysMenu m where m.isEnable = ?1")
    List<SysMenu> getSysMenu(boolean isEnable);

    /**
     * 根据用户获取菜单
     * @param userId
     * @return
     */
    @Query("select o from SysMenu o where o.id in (select t.menuId from SysUserMenu t where t.userId = ?1)")
    List<SysMenu> getMenusByUser(Integer userId);

    /**
     * 根据菜单类型获取菜单
     * @param menyType
     * @return
     */
    @Query("select o from SysMenu o where o.menuType = ?1")
    SysMenu getSysMenuByType(String menyType);
}
