package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zqLuo
 * 系统配置文件
 */
@Configuration
public class SystemProperties {
    @Value("${fileRootPath}")
    private String fileRootPath;


    public String getFileRootPath() {
        return fileRootPath;
    }

    public void setFileRootPath(String fileRootPath) {
        this.fileRootPath = fileRootPath;
    }
}
