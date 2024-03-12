package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.model.OrderDto;
import java.time.LocalDateTime;

public interface IOrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto updateOrderDeliveredStatus(String uuid, LocalDateTime timestamp);
}
