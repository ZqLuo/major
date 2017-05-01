package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 销售
 * Created by zqLuo
 */
@Entity
@Table(name = "market")
public class Market {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 销售编号 yyyyMMddHHmmss
     */
    @Column(name = "market_no",length = 14)
    private String marketNo;
    /**
     * 销售金额
     */
    @Column(name = "price")
    private Double price;
    /**
     * 销售日期
     */
    @Column(name = "market_date")
    private Date marketDate;
    @Transient
    private String marketDateStr;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 客户名称
     */
    @Column(name = "customer_name",length = 32)
    private String customerName;
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

    public String getMarketNo() {
        return marketNo;
    }

    public void setMarketNo(String marketNo) {
        this.marketNo = marketNo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag;
    }

    public String getMarketDateStr() {
        return marketDateStr;
    }

    public void setMarketDateStr(String marketDateStr) {
        this.marketDateStr = marketDateStr;
    }
}
