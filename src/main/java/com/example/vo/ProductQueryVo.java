package com.example.vo;

import com.example.entity.Product;

import java.util.Date;

/**
 * 商品查询条件封装
 * Created by zqLuo
 */
public class ProductQueryVo{

    private Integer id;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 商品类型
     */
    private String productType;
    private String productTypeName;
    /**
     * 计量单位
     */
    private String measurementUnit;
    private String measurementUnitName;
    /**
     * 进货日期
     */
    private String purchaseDate;
    /**
     * 数量
     */
    private Double quantity;
    /**
     * 进价
     */
    private Double price;
    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getMeasurementUnitName() {
        return measurementUnitName;
    }

    public void setMeasurementUnitName(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }

}
