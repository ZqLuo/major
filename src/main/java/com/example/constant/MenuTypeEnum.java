package com.example.constant;

/**
 * 菜单类型枚举
 * Created by zqLuo
 */
public enum MenuTypeEnum {

    MARKET_MENU("marketMenu","销售管理菜单"),
    PRODUCT_MENU("productMenu","商品管理菜单");


    private String menyType;

    private String name;

    MenuTypeEnum(String menyType, String name) {
        this.menyType = menyType;
        this.name = name;
    }

    public String getMenyType() {
        return menyType;
    }

    public void setMenyType(String menyType) {
        this.menyType = menyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
