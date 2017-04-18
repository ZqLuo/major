package com.example.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zqLuo
 */
@Controller
public class IndexController {

    private final Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(){
        logger.info("进入首页");
        return "index";
    }

}
