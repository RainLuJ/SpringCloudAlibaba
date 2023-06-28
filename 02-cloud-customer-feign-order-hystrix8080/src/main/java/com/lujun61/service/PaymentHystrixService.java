package com.lujun61.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("CLOUD-PAYMENT-SERVICE-HYSTRIX")
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/exception/{id}")
    String paymentInfo_Exception(@PathVariable("id") Integer id);

}
