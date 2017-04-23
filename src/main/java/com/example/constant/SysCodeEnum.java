package com.example.constant;

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
}
