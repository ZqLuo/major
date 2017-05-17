package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 进货历史纪录
 * Created by zqLuo
 */
@Entity
@Table(name = "product_history")
public class ProductHistory {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 进货日期
     */
    @Column(name = "in_date")
    private Date inDate;
    @Transient
    private String inDateValue;
    /**
     * 销售商品
     */
    @OneToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    /**
     * 单价
     */
    @Column(name = "price",nullable = false)
    private Double price;

    /**
     * 总数
     */
    @Column(name = "quantity",nullable = false)
    private Double quantity;

    /**
     * 总价
     */
    @Column(name = "total_price",nullable = false)
    private Double totalPrice;

    /**
     * 备注信息
     */
    @Column(name = "remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getInDateValue() {
        return inDateValue;
    }

    public void setInDateValue(String inDateValue) {
        this.inDateValue = inDateValue;
    }
}
