package com.example.dao;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
}
