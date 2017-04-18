package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * API.properties
 * Created by zqLuo
 */
@Component
@PropertySource("classpath:config/API.properties")
@ConfigurationProperties
public class APIProperties {
    /**
     * showapi平台链接
     */
    public String showapihost;
    /**
     * showapi APPCODE
     */
    public String expressappcode;

    public String getShowapihost() {
        return showapihost;
    }

    public void setShowapihost(String showapihost) {
        this.showapihost = showapihost;
    }

    public String getExpressappcode() {
        return expressappcode;
    }

    public void setExpressappcode(String expressappcode) {
        this.expressappcode = expressappcode;
    }
}
