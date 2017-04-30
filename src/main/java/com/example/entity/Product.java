package com.example.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

/**
 * 商品
 * Created by zqLuo
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 商品编号
     */
    @Column(name = "product_no",length = 32,nullable = false)
    private String productNo;
    /**
     * 商品类型
     */
    @Column(name = "product_type",length = 32,nullable = false)
    private String productType;
    @Transient
    private String productTypeName;
    /**
     * 计量单位
     */
    @Column(name = "measurement_unit",length = 10)
    private String measurementUnit;
    @Transient
    private String measurementUnitName;
    /**
     * 进货日期
     */
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Transient
    private String purchaseDateStr;
    /**
     * 数量
     */
    @Column(name = "quantity",nullable = false)
    private Double quantity;
    /**
     * 进价
     */
    @Column(name = "price")
    private Double price;
    /**
     * 备注
     */
    @Column(name = "remark",length = 512)
    private String remark;
    /**
     * 删除标识
     */
    @Column(name = "delete_tag",length = 1)
    private String deleteTag = "1";

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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
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

    public String getPurchaseDateStr() {
        return purchaseDateStr;
    }

    public void setPurchaseDateStr(String purchaseDateStr) {
        this.purchaseDateStr = purchaseDateStr;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag;
    }
}
