package com.restaurant.grandmasfood.mapper.impl;

import com.restaurant.grandmasfood.entity.Order;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderToDtoMapper implements Mapper<Order, OrderDto> {

    @Override
    public OrderDto mapToDto(Order order) {
        return OrderDto.builder()
                .uuid(order.getUuid())
                .creationDateTime(order.getCreationDateTime())
                .clientDocument(order.getClient())
                .productUuid(order.getProduct())
                .quantity(order.getQuantity())
                .extraInformation(order.getExtraInformation())
                .subTotal(order.getSubTotal())
                .tax(order.getTax())
                .grandTotal(order.getGrandTotal())
                .delivered(order.getDelivered())
                .deliveredDate(order.getDeliveredDate())
                .build();
    }


    @Override
    public Order mapFromDto(OrderDto orderDto) {
        return Order.builder()
                .uuid(orderDto.getUuid())
                .creationDateTime(orderDto.getCreationDateTime())
                .client(orderDto.getClientDocument())
                .product(orderDto.getProductUuid())
                .quantity(orderDto.getQuantity())
                .extraInformation(orderDto.getExtraInformation())
                .subTotal(orderDto.getSubTotal())
                .tax(orderDto.getTax())
                .grandTotal(orderDto.getGrandTotal())
                .delivered(orderDto.getDelivered())
                .deliveredDate(orderDto.getDeliveredDate())
                .build();
    }
}
