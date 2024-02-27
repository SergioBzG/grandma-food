package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.service.IOrderService;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements IOrderService {

    @Override
    public String createOrder() {
        return "Order created";
    }

    @Override
    public String deliverOrder() {
        return "Order delivered";
    }
}
