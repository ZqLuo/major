package com.example.API.express;

import com.alibaba.fastjson.JSON;
import com.example.config.APIProperties;
import com.example.util.HttpUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 快递查询工具
 * Created by zqLuo
 */
@Service
public class ExpressQueryUtil {

    private static Logger logger = Logger.getLogger(ExpressQueryUtil.class);

    @Autowired
    private APIProperties API;

    /**
     * 快递单号查询
     * @return
     */
    public  ShowapiExpInfo showapiExpInfo(String com,String nu){
        //单号查询默认方法
        String path = "/showapi_expInfo";
        ShowapiExpInfo showapiExpInfo = new ShowapiExpInfo();
        if(StringUtils.isEmpty(nu)){
            showapiExpInfo.setShowapi_res_code("-1");
            showapiExpInfo.setShowapi_res_error("快递单号为空");
            return showapiExpInfo;
        }
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + API.getExpressappcode());
        Map<String, String> query = new HashMap<String, String>();
        query.put("com",StringUtils.isEmpty(com) ? "auto" : com);
        query.put("nu",nu);
        try {
            HttpResponse response = HttpUtils.doGet(API.getShowapihost(),path,"GET",headers,query);
            String result = EntityUtils.toString(response.getEntity());
            showapiExpInfo = JSON.parseObject(result,ShowapiExpInfo.class);
            logger.info(result);
        } catch (Exception e) {
            logger.error("快递信息查询失败",e);
            showapiExpInfo.setShowapi_res_code("-1");
            showapiExpInfo.setShowapi_res_error("查询失败");
        }
        return showapiExpInfo;
    }

    /**
     * 单号查快递公司名
     * @param nu 可选	快递单号
     * @return
     */
    public FetchCom fetchCom(String nu){
        String path = "/fetchCom";
        FetchCom fetchCom = null;
        if(StringUtils.isEmpty(nu)){
            fetchCom = new FetchCom();
            fetchCom.setShowapi_res_code("-1");
            fetchCom.setShowapi_res_error("快递单号为空");
            return fetchCom;
        }
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + API.getExpressappcode());
        Map<String, String> query = new HashMap<String, String>();
        query.put("nu",nu);
        try {
            HttpResponse response = HttpUtils.doGet(API.getShowapihost(),path,"GET",headers,query);
            String result = EntityUtils.toString(response.getEntity());
            fetchCom = JSON.parseObject(result,FetchCom.class);
            logger.info(result);
        } catch (Exception e) {
            logger.error("快递信息查询失败",e);
            fetchCom.setShowapi_res_code("-1");
            fetchCom.setShowapi_res_error("查询失败");
        }
        return fetchCom;
    }

    /**
     * 快递公司查询
     * @param expName 可选	快递/物流公司名称
     * @param maxSize 可选	分页时,每页返回的最大记录数
     * @param page    可选	分页的页数
     * @return
     */
    public ExpressList expressList(String expName,String maxSize,String page){
        String path = "/showapi_expressList";
        ExpressList expressList = null;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + API.getExpressappcode());
        Map<String, String> query = new HashMap<String, String>();
        if(StringUtils.isNotEmpty(expName)){
            query.put("expName",expName);
        }
        if(StringUtils.isNotEmpty(maxSize)){
            query.put("maxSize",maxSize);
        }
        if(StringUtils.isNotEmpty(page)){
            query.put("page",page);
        }
        try {
            HttpResponse response = HttpUtils.doGet(API.getShowapihost(),path,"GET",headers,query);
            String result = EntityUtils.toString(response.getEntity());
            expressList = JSON.parseObject(result,ExpressList.class);
            logger.info(result);
        } catch (Exception e) {
            logger.error("快递信息查询失败",e);
            expressList.setShowapi_res_code("-1");
            expressList.setShowapi_res_error("查询失败");
        }
        return expressList;
    }
}
