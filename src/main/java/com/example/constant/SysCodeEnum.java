package com.example.constant;

/**
 * 系统编码枚举
 * Created by zqLuo
 */
public enum SysCodeEnum {

    PRODUCT_TYPE("productType","商品类型"),
    MEASUREMENT_UNIT("measurementUnit","计量单位")
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
