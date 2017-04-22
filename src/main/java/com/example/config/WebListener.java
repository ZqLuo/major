package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * Created by zqLuo
 */
@Configuration
public class WebListener {

    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

}
