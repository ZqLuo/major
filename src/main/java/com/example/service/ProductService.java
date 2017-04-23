package com.example.service;

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
}
