package com.example.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 快递查询
 * Created by zqLuo
 */
@Entity
@Table(name = "express_query")
public class ExpressQuery {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 快递单号
     */
    @Column(name = "express_no")
    private String expressNo;

    /**
     * 查询结果
     */
    @Column(name = "result",length = 1024)
    private String result;

    /**
     * 上次查询时间
     */
    @Column(name = "lastquery_date")
    private Date lastQueryDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getLastQueryDate() {
        return lastQueryDate;
    }

    public void setLastQueryDate(Date lastQueryDate) {
        this.lastQueryDate = lastQueryDate;
    }
}
