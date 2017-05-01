package com.example.service;

import com.example.entity.Market;
import com.example.entity.MarketDetail;
import com.example.util.PageReturn;
import com.example.vo.MarketQueryVo;

import java.util.List;

/**
 * 销售管理
 * Created by zqLuo
 */
public interface MarketService {

    /**
     * 获取商品列表
     * @param marketQueryVo
     * @return
     */
    PageReturn getMarketList(MarketQueryVo marketQueryVo,int page, int size);

    /**
     * 根据销售编号获取销售订单
     * @param id
     * @return
     */
    Market getMarketById(String id);

    /**
     * 根据销售编号获取销售详细列表
     * @param marketId
     * @return
     */
    List<MarketDetail> getMarketDetailListByMarketId(String marketId);

    /**
     * 保存或更新销售订单
     * @param market
     */
    void saveOrUpdateMarket(Market market);

    /**
     * 删除销售订单，逻辑删除
     * @param id
     */
    void delMarket(String id);

    /**
     * 删除销售详细
     * @param id
     */
    void delMarketDeatil(String id);

    /**
     * 保存或修改销售详细
     * @param marketDetail
     */
    void saveOrUpdateMarketDeatil(MarketDetail marketDetail);
}
