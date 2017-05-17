package com.example.service;

import com.example.entity.Product;
import com.example.entity.ProductHistory;
import com.example.util.PageReturn;
import com.example.vo.ProductQueryVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 删除商品，逻辑删除
     * @param id
     */
    void delProduct(String id);

    /**
     * 根据商品类型获取商品编号
     * @param productType
     * @return
     */
    List<Map<String,Object>> getProductNoByProductype(String productType);

    /**
     * 获取商品历史纪录
     * @param productId
     * @return
     */
    PageReturn getProductHistoryList(String productId,int page,int size);

    /**
     * 保存或更新进货历史
     * @param productHistory
     */
    ProductHistory saveOrUpdateProductHistory(ProductHistory productHistory) throws Exception;

    /**
     * 获取商品历史
     * @param id
     * @return
     */
    ProductHistory getProductHistoryById(String id);

    /**
     * 更新商品进货总数和总金额
     * @param productId
     */
    void updateProductPriceAndQuantity(Integer productId);

    /**
     * 删除进货历史
     * @param id
     */
    void removeProductHistory(String id);

    /**
     * 更新商品库存
     * @param id
     */
    void updateProductResidueQuantity(String id);
}
