package com.example.dao;

import com.example.entity.MarketDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zqLuo
 */
public interface MarketDetailRepository extends JpaRepository<MarketDetail,Integer> {

    /**
     * 根据销售编号获取销售详细列表
     * @param marketId
     * @return
     */
    @Query("select o from MarketDetail o where o.market.id = ?1")
    List<MarketDetail> getMarketDetailListByMarketId(Integer marketId);

    /**
     * 删除销售详细
     * @param id
     */
    @Modifying
    @Query("delete from MarketDetail m where m.id = ?1")
    void delMarketDeatil(int id);

    @Query("select sum(m.totalPrice) from MarketDetail m where m.market.id = ?1")
    Double getMarketTotalPriceByMarketId(Integer id);
}
