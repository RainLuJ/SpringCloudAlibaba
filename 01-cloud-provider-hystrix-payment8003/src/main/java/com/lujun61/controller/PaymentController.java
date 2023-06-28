package com.lujun61.controller;

import com.lujun61.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        System.out.println("paymentInfo_OKKKKOKKK");
        return paymentService.paymentinfo_Ok(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        System.out.println("paymentInfo_timeout");
        return paymentService.paymentinfo_Timeout(id);
    }

    @GetMapping("/payment/hystrix/exception/{id}")
    public String paymentInfo_Exception(@PathVariable("id") Integer id) {
        System.out.println("paymentInfo_exception");
        return paymentService.paymentinfo_Timeout(id);
    }

    /**
     * @description 测试服务熔断
     * @author Jun Lu
     * @date 2023-06-28 11:38:12
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        return paymentService.paymentCircuitBreaker(id);
    }
}

