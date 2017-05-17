package com.example.dao;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zqLuo
 */
public interface ProductRepository extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product> {
    /**
     * 根据商品编号和商品类型获取商品
     * @param productNo
     * @param productType
     * @return
     */
    @Query("select o from Product o where  o.productNo = ?1 and o.productType = ?2")
    Product getProductByNoAndProductType(String productNo, String productType);

    /**
     * 更新商品库存
     * @param id
     */
    @Modifying
    @Query(value = "update product set quantity = " +
            "((select case when sum(quantity) is null then 0 else sum(quantity) end from product_history where product_id = ?1) " +
            " - " +
            "(select case when sum(quantity) is null then 0 else sum(quantity) end from market_detail where product_id = ?1) )  " +
            "where id = ?1",nativeQuery = true)
    void updateProductResidueQuantity(int id);
}
