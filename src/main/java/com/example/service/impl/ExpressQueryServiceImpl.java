package com.example.service.impl;

import com.example.dao.ExpressQueryRepository;
import com.example.entity.ExpressQuery;
import com.example.service.ExpressQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class ExpressQueryServiceImpl implements ExpressQueryService {

    @Autowired
    private ExpressQueryRepository expressQueryRepository;

    @Override
    public ExpressQuery getExpressQueryByNo(String expressNo) {
        return expressQueryRepository.getExpressQueryByNo(expressNo);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveOrUpdateExpressQuery(ExpressQuery expressQuery) {
        expressQueryRepository.save(expressQuery);
    }
}
