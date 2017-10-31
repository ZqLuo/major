package com.example.service;

import com.example.entity.IncomeBill;
import com.example.util.PageReturn;

/**
 * Created by zqLuo
 */
public interface IncomeBillService {

    void insert(IncomeBill incomeBill);

    /**
     * 获取列表
     * @param incomeBill
     * @param page
     * @param size
     * @return
     */
    PageReturn getIncomeBillList(IncomeBill incomeBill, int page, int size);

    IncomeBill getEntityById(String id);

    void delele(String id);
}
