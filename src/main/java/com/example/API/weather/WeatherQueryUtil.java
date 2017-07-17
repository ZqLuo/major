package com.example.API.weather;

import com.example.config.APIProperties;
import com.example.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zqLuo
 */
@Service
public class WeatherQueryUtil {

    private static Logger logger = Logger.getLogger(WeatherQueryUtil.class);

    @Autowired
    private APIProperties API;

    //根据地区获取天气预报
    public String areaToWeather(String areaName) {
        String appcode = API.weatherappcode;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", areaName);
        querys.put("areaid", "");
        querys.put("need3HourForcast", "0"); //可选	是否需要每小时数据的累积数组。由于本系统是半小时刷一次实时状态，因此实时数组最大长度为48。每天0点长度初始化为0. 1为需要 0为不
        querys.put("needAlarm", "0"); //可选	是否需要天气预警。1为需要，0为不需要。
        querys.put("needHourData", "0");//可选	是否需要每小时数据的累积数组。由于本系统是半小时刷一次实时状态，因此实时数组最大长度为48。每天0点长度初始化为0.
        querys.put("needIndex", "0"); //可选	是否需要返回指数数据，比如穿衣指数、紫外线指数等。1为返回，0为不返回。
        querys.put("needMoreDay", "0"); //可选	是否需要返回7天数据中的后4天。1为返回，0为不返回。

        try {
            HttpResponse response = HttpUtils.doGet(API.getWeatherhost(),"/area-to-weather","GET",headers,querys);
            if(response.getStatusLine().getStatusCode() == 404){
                logger.error("天气查询网址无效：" + API.getWeatherhost() + "/area-to-weather" + ",APPCODE:" + API.weatherappcode);
                return "天气查询网址无效：" + API.getWeatherhost() + "/area-to-weather" + ",APPCODE:" + API.weatherappcode;
            }else{
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("快递信息查询失败",e);
        }
        return null;
    };

}
