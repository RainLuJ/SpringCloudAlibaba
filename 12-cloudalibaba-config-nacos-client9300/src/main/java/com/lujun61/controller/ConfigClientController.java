package com.lujun61.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*  TODO: (Nacos Config - 1) 被@RefreshScope注解的bean可以被动态刷新~
        由于Nacos自带动态刷新功能，所以这里只需要加上@RefreshScope注解就行！！
 */
@RefreshScope
@RestController
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}

