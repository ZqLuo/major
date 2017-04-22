package com.example.service;

import com.example.util.PageForSql;
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
    PageForSql getProductList(ProductQueryVo productQuerty,int page,int size);
}
