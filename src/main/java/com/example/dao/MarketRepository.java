package com.example.dao;

import com.example.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by zqLuo
 */
public interface MarketRepository extends JpaRepository<Market,Integer> {
    /**
     * 逻辑删除销售订单
     * @param id
     */
    @Modifying
    @Query("update Market m set m.deleteTag = '0' where m.id = ?1")
    void delMarket(int id);
}
