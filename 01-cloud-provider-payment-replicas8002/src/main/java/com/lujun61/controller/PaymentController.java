package com.lujun61.controller;

import com.lujun61.entity.CommonResult;
import com.lujun61.entity.Payment;
import com.lujun61.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentServiceImpl;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        Integer result = paymentServiceImpl.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentServiceImpl.getPaymentById(id);

        if (payment != null) {
            return new CommonResult<>(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录,查询ID: " + id, null);
        }
    }
}
