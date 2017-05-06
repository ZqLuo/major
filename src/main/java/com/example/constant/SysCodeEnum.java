package com.example.constant;

import com.example.entity.SysCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统编码枚举
 * Created by zqLuo
 */
public enum SysCodeEnum {
    /**
     * 商品类型
     */
    PRODUCT_TYPE("productType","商品类型"),
    /**
     * 计量单位
     */
    MEASUREMENT_UNIT("measurementUnit","计量单位"),
    /**
     * 菜单类型
     */
    MENU_TYPE("menuType","菜单类型")
    ;


    private String type;
    private String name;

    SysCodeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<SysCode> getAllSysCodeType(){
        List<SysCode> sysCodes = new ArrayList<SysCode>();
        for(SysCodeEnum sysCodeEnum : SysCodeEnum.values()){
            SysCode sysCode = new SysCode();
            sysCode.setCode(sysCodeEnum.getType());
            sysCode.setName(sysCodeEnum.getName());
            sysCodes.add(sysCode);
        }
        return sysCodes;
    }
}
