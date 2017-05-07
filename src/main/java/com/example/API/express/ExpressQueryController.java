package com.example.API.express;

import com.alibaba.fastjson.JSON;
import com.example.controller.BaseController;
import com.example.entity.ExpressQuery;
import com.example.service.DemoService;
import com.example.service.ExpressQueryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 快递查询Controller
 * Created by zqLuo
 */
@RestController
@RequestMapping("/expressQuery")
public class ExpressQueryController extends BaseController{


    @Autowired
    private ExpressQueryUtil expressQueryUtil;
    @Autowired
    private ExpressQueryService expressQueryService;

    Logger logger = Logger.getLogger(ExpressQueryController.class);

    @RequestMapping("showapiExpInfo")
    public ShowapiExpInfo showapiExpInfo(String com,String nu){
        ExpressQuery expressQuery = expressQueryService.getExpressQueryByNo(nu);
        ShowapiExpInfo showapiExpInfo = null;
        if(expressQuery == null){
            expressQuery = new ExpressQuery();
            expressQuery.setExpressNo(nu);
            showapiExpInfo =  expressQueryUtil.showapiExpInfo(com,nu);
            expressQuery.setResult(JSON.toJSON(showapiExpInfo).toString());
            expressQuery.setLastQueryDate(new Date());
            expressQueryService.saveOrUpdateExpressQuery(expressQuery);
        }else{
            Date last = expressQuery.getLastQueryDate();
            //距离上次查询时间超过15分钟
            if(new Date().getTime() - last.getTime() >= (60*1000*15)){
                showapiExpInfo =  expressQueryUtil.showapiExpInfo(com,nu);
                expressQuery.setResult(JSON.toJSON(showapiExpInfo).toString());
                expressQueryService.saveOrUpdateExpressQuery(expressQuery);
            }else{
                showapiExpInfo =  JSON.parseObject(expressQuery.getResult(),ShowapiExpInfo.class);
            }
        }
        logger.info(getIpAddress() + "，快递：" + nu);
        return showapiExpInfo;
    }

    @RequestMapping("fetchCom")
    public FetchCom fetchCom(String nu){
        return expressQueryUtil.fetchCom(nu);
    }

    @RequestMapping("expressList")
    public ExpressList expressList(String expName,String maxSize,String page){
        return expressQueryUtil.expressList(expName,maxSize,page);
    }
}
