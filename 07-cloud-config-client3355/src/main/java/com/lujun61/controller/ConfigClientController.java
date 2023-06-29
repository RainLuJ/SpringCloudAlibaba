package com.lujun61.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 实现Config Client动态刷新需要添加此注解
@RefreshScope
public class ConfigClientController {

    // 在配置了分布式配置中心后，就把config服务端里的配置和资源引入进来了
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}

