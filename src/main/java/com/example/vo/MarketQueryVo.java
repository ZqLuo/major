package com.example.vo;

/**
 * 销售查询对象
 * Created by zqLuo
 */
public class MarketQueryVo {

    /**
     * 销售编号 yyyyMMddHHmmss
     */
    private String marketNo;
    /**
     * 销售金额
     */
    private Double price;
    /**
     * 销售日期
     */
    private String marketDateBegin;
    private String marketDateEnd;
    /**
     * 备注
     */
    private String remark;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 商品类型
     */
    private String productType;

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

    public String getMarketDateBegin() {
        return marketDateBegin;
    }

    public void setMarketDateBegin(String marketDateBegin) {
        this.marketDateBegin = marketDateBegin;
    }

    public String getMarketDateEnd() {
        return marketDateEnd;
    }

    public void setMarketDateEnd(String marketDateEnd) {
        this.marketDateEnd = marketDateEnd;
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
}
