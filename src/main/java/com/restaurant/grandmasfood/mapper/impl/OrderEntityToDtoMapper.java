package com.restaurant.grandmasfood.mapper.impl;

import com.restaurant.grandmasfood.entity.OrderEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToDtoMapper implements Mapper<OrderEntity, OrderDto> {

    @Override
    public OrderDto mapToDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .uuid(orderEntity.getUuid())
                .creationDateTime(orderEntity.getCreationDateTime())
                .clientDocument(orderEntity.getClientEntity())
                .productUuid(orderEntity.getProductEntity())
                .quantity(orderEntity.getQuantity())
                .extraInformation(orderEntity.getExtraInformation())
                .subTotal(orderEntity.getSubTotal())
                .tax(orderEntity.getTax())
                .grandTotal(orderEntity.getGrandTotal())
                .delivered(orderEntity.getDelivered())
                .deliveredDate(orderEntity.getDeliveredDate())
                .build();
    }


    @Override
    public OrderEntity mapFromDto(OrderDto orderDto) {
        return OrderEntity.builder()
                .uuid(orderDto.getUuid())
                .creationDateTime(orderDto.getCreationDateTime())
                .clientEntity(orderDto.getClientDocument())
                .productEntity(orderDto.getProductUuid())
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
