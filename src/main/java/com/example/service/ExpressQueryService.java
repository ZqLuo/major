package com.example.service;

import com.example.entity.ExpressQuery;

/**
 * 快递查询
 * Created by zqLuo
 */
public interface ExpressQueryService {

    /**
     * 根据快递单号查询快递查询信息
     * @param expressNo
     * @return
     */
    ExpressQuery getExpressQueryByNo(String expressNo);

    /**
     * 保存或更新
     * @param expressQuery
     */
    void saveOrUpdateExpressQuery(ExpressQuery expressQuery);
}
