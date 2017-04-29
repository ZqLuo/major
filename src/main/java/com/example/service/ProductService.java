package com.example.service;

import com.example.entity.Product;
import com.example.util.PageReturn;
import com.example.vo.ProductQueryVo;

/**
 * 商品service
 * Created by zqLuo
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     * @param productQuerty
     * @param page
     * @param size
     * @return
     */
    PageReturn getProductList(ProductQueryVo productQuerty, int page, int size);

    /**
     * 根据ID获取商品
     * @return
     */
    Product getProductById(Integer id);

    /**
     * 新增或修改商品
     * @param product
     */
    void createOrUpdateProduct(Product product);

    /**
     * 根据商品编号和商品类型获取商品
     * @param productNo
     * @param productType
     * @return
     */
    Product getProductByNoAndProductType(String productNo, String productType);
}
