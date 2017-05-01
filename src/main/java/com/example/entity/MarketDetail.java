package com.example.entity;

import javax.persistence.*;
import java.util.DoubleSummaryStatistics;

/**
 * 销售详细
 * Created by zqLuo
 */
@Entity
@Table(name = "market_detail")
public class MarketDetail {

    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 销售商品
     */
    @OneToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;
    /**
     * 销售订单
     */
    @OneToOne
    @JoinColumn(name = "market_id",nullable = false)
    private Market market;
    /**
     * 数量
     */
    @Column(name = "quantity")
    private Double quantity;
    /**
     * 单价
     */
    @Column(name = "single_price")
    private Double singlePrice;
    /**
     * 总价
     */
    @Column(name = "total_price")
    private Double totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
