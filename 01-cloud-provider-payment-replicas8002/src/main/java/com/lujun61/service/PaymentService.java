package com.lujun61.service;

import com.lujun61.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    Integer create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}

