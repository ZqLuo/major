package com.example.API.express;

import com.example.service.DemoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 快递查询Controller
 * Created by zqLuo
 */
@RestController
@RequestMapping("/expressQuery")
public class ExpressQueryController {

    @Autowired
    private ExpressQueryUtil expressQueryUtil;
    @Autowired
    private DemoService demoService;

    Logger logger = Logger.getLogger(ExpressQueryController.class);

    @RequestMapping("showapiExpInfo")
    public ShowapiExpInfo showapiExpInfo(String com,String nu){
        return expressQueryUtil.showapiExpInfo(com,nu);
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
