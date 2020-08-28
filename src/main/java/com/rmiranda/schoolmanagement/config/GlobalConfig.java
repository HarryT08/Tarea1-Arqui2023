package com.rmiranda.schoolmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
    
    @Value("${schoolmanagement.application.title}")
    private String appName;

    @Value("${schoolmanagement.application.slogan}")
    private String appSlogan;

    public String getAppName() {
        return appName;
    }

    public String getAppSlogan() {
        return appSlogan;
    }
}