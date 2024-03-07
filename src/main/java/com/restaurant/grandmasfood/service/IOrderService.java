package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.model.OrderDto;

public interface IOrderService {
    OrderDto createOrder(OrderDto orderDto) throws AlreadyExistsException;

    String deliverOrder();
}
