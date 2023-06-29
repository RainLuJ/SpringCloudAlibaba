package com.lujun61.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

//@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("***********come in MyLogGateWayFilter:  " + new Date());

        //  获取类似于HttpRequest的请求体对象.获取查询携带的参数值.获取给定Map的第一个键值对（第一个参数）
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if (uname == null) {
            System.out.println("*******用户名为null，非法用户，o(╥﹏╥)o");
            // 获取类似于HttpResponse的响应体对象.设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    // 值越小，过滤器越先被执行
    @Override
    public int getOrder() {
        return 0;
    }
}

