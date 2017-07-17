package com.example.API.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zqLuo
 */
@RestController
public class WeatherQueryController {

    @Autowired
    private WeatherQueryUtil weatherQueryUtil;

    @GetMapping("/weather/{areaName}")
    public String queryWeather(@PathVariable String areaName){
        return weatherQueryUtil.areaToWeather(areaName);
    }

}
