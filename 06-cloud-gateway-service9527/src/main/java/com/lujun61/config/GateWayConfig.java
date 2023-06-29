package com.lujun61.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

/**
 * @description 不使用yml配置，而是使用JavaConfig配置的方式进行Gateway路由的配置
 * @author Jun Lu
 * @date 2023-06-29 14:38:51
 */
//@Configuration
public class GateWayConfig {
    //@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        // 分别是id，本地址，转发到的地址
        routes.route("path_route_atguigu",  // 路由id
                r -> r.path("/guonei")      // 断言
                        .uri("http://news.baidu.com/guonei")  // 转发的地址
        ).build();

        return routes.build();
    }
}

