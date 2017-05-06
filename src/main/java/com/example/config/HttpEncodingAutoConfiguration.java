package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * http编码配置
 */
@Configuration
@EnableConfigurationProperties(HttpEncodingProperties.class) //开启属性注入
@ConditionalOnClass(CharacterEncodingFilter.class) //当CharacterEncodingFilter在类路径下
//当配置文件spring.http.encoding=enable的时候，若为空默认true
@ConditionalOnProperty(prefix = "spring.http.encoding",value = "enable",matchIfMissing = true)

public class HttpEncodingAutoConfiguration {

    @Autowired
    private HttpEncodingProperties httpEncodingProperties;


    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class) //当容器中没有这个bean的时候新建
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(httpEncodingProperties.getCharset().name());
        filter.setForceEncoding(httpEncodingProperties.isForce());
        return filter;
    }


}
