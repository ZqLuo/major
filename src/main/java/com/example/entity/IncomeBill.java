package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 收入支出记录
 * Created by zqLuo
 */
@Entity
@Table(name = "INCOME_BILL")
public class IncomeBill {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 数量
     */
    @Column(name = "count")
    private Float count;

    /**
     * 类型 1：收入，2：花销
     */
    @Column(name = "type",length = 1)
    private String type;

    /**
     * 备注
     */
    @Column(name = "remark",length = 2000)
    private String remark;
    /**
     * 记录日期
     */
    @Column(name = "create_time",length = 2000)
    private Date createTime;
    @Transient
    private String createTimeStr;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getCount() {
        return count;
    }

    public void setCount(Float count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
