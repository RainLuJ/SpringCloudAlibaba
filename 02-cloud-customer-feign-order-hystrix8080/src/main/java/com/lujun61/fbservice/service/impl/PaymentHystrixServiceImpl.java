package com.lujun61.fbservice.service.impl;

import com.lujun61.fbservice.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @description 目的：统一为PaymentHystrixService接口中的方法配置【降级[fallback]方法】
 *          方法：Service层的降级方法的实现，就是对对应的Service进行实现，
 *          并在接口上使用@FeignClient注解，然后添加`fallback`属性指定其实现类。
 * @author Jun Lu
 * @date 2023-06-28 11:10:22
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "我是消费者80，对Service方法进行降级~（1）";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "我是消费者80，对Service方法进行降级~（2）";
    }

    @Override
    public String paymentInfo_Exception(Integer id) {
        return "我是消费者80，对Service方法进行降级~（3）";
    }
}
