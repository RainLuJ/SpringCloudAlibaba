package com.lujun61.dao;

import com.lujun61.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper // 是ibatis下面的注解 //@Repositoty有时候会有问题
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
