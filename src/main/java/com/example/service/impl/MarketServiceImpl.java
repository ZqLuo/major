package com.example.service.impl;

import com.example.dao.MarketDetailRepository;
import com.example.dao.MarketRepository;
import com.example.entity.Market;
import com.example.entity.MarketDetail;
import com.example.service.MarketService;
import com.example.util.DateUtils;
import com.example.util.JdbcUtil;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.MarketQueryVo;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private JdbcUtil<Market> jdbcUtil;
    @Autowired
    private MarketDetailRepository marketDetailRepository;

    @Override
    public PageReturn getMarketList(MarketQueryVo marketQueryVo,int page, int size) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("select m.id, customer_name customerName, market_date marketDate, market_no marketNo, price, remark from market m where m.delete_tag = '1' ");

        sql.append(" order by m.market_date desc ");
        return jdbcUtil.queryForPage(sql.toString(),page,size,Market.class,params.toArray());
    }

    @Override
    public Market getMarketById(String id) {
        return marketRepository.findOne(Integer.parseInt(id));
    }

    @Override
    public List<MarketDetail> getMarketDetailListByMarketId(String marketId) {
        return marketDetailRepository.getMarketDetailListByMarketId(Integer.parseInt(marketId));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveOrUpdateMarket(Market market) {
        if(StringUtil.isNotEmpty(market.getMarketDateStr())){
            market.setMarketDate(DateUtils.parse(market.getMarketDateStr(),DateUtils.datetimeFormat));
        }else{
            market.setMarketDate(new Date());
        }
        marketRepository.save(market);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void delMarket(String id) {
        marketRepository.delMarket(Integer.parseInt(id));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void delMarketDeatil(String id) {
        marketDetailRepository.delMarketDeatil(Integer.parseInt(id));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void saveOrUpdateMarketDeatil(MarketDetail marketDetail) {
        marketDetailRepository.save(marketDetail);
    }
}
