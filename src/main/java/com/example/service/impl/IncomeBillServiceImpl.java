package com.example.service.impl;

import com.example.dao.IncomeBillRepository;
import com.example.entity.IncomeBill;
import com.example.service.IncomeBillService;
import com.example.util.JdbcUtil;
import com.example.util.PageReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class IncomeBillServiceImpl implements IncomeBillService {

    @Autowired
    private JdbcUtil<IncomeBill> jdbcUtil;

    @Autowired
    private IncomeBillRepository incomeBillRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(IncomeBill incomeBill) {
        incomeBill.setCreateTime(new Date());
        incomeBillRepository.save(incomeBill);
    }

    @Override
    public PageReturn getIncomeBillList(IncomeBill incomeBill, int page, int size) {
        String sql = "select id,count,type,remark,create_time createTime from income_bill";
        return jdbcUtil.queryForPage(sql.toString(),page,size,IncomeBill.class,null);
    }

    @Override
    public IncomeBill getEntityById(String id) {
        return incomeBillRepository.findOne(Integer.parseInt(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delele(String id) {
        incomeBillRepository.delete(Integer.parseInt(id));
    }
}
