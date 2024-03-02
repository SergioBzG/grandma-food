package com.restaurant.grandmasfood.mapper;

import com.restaurant.grandmasfood.entity.OrderEntity;
import com.restaurant.grandmasfood.model.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityToDtoMapper implements Mapper<OrderEntity, OrderDto> {


    @Override
    public OrderDto mapToDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .quantity(orderEntity.getQuantity())
                .additionalInfo(orderEntity.getAdditionalInfo())
                .delivered(orderEntity.getDelivered())
                .dateOrdered(orderEntity.getDateOrdered())
                .dateDelivered(orderEntity.getDateDelivered())
                .iva(orderEntity.getIva())
                .subTotal(orderEntity.getSubTotal())
                .total(orderEntity.getTotal())
                .build();
    }

    @Override
    public OrderEntity mapFromDto(OrderDto orderDto) {
        return OrderEntity.builder()
                .quantity(orderDto.getQuantity())
                .additionalInfo(orderDto.getAdditionalInfo())
                .dateOrdered(orderDto.getDateOrdered())
                .dateDelivered(orderDto.getDateDelivered())
                .subTotal(orderDto.getSubTotal())
                .iva(orderDto.getIva())
                .total(orderDto.getTotal())
                .delivered(orderDto.getDelivered())
                .build();
    }
}
