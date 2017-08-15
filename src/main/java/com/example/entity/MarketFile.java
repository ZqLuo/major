package com.example.entity;

import javax.persistence.*;

/**
 * Created by zqLuo
 * 销售文档
 */
@Entity
@Table(name = "market_file")
public class MarketFile {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 销售订单
     */
    @OneToOne
    @JoinColumn(name = "market_id",nullable = false)
    private Market market;
    /**
     * 文件名称
     */
    @Column(name = "file_name",nullable = false)
    private String fileName;
    /**
     * 文件路径
     */
    @Column(name = "file_path",nullable = false)
    private String filePath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
