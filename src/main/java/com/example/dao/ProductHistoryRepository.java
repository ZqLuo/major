package com.example.dao;

import com.example.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by zqLuo
 */
public interface ProductHistoryRepository extends JpaRepository<ProductHistory,Integer>{

    /**
     * 更新商品总价，单价，总数
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update product p left join " +
            "(select product_id,sum(total_price) totalPrice,sum(quantity) totalQuantity,avg(price) avgPrice,max(in_date) indate from product_history where product_id = ?1 group by product_id) ph " +
            "on ph.product_id = p.id " +
            "set p.price = ph.avgPrice ,p.total_price = ph.totalPrice,p.total_quatity = ph.totalQuantity, p.purchase_date = ph.indate " +
            "where p.id = ?1",nativeQuery = true)
    void updateProductPriceAndQuantity(Integer id);
}
