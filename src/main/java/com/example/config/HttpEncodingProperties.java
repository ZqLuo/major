package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * http编码配置文件
 */
@Component
@ConfigurationProperties(prefix = "spring.http.encoding")
public class HttpEncodingProperties {

    public  static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    //编码默认utf-8
    private Charset charset = DEFAULT_CHARSET;
    //设置forceEncoding
    private boolean force = true;


    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}
