package com.restaurant.grandmasfood.mapper.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.OrderEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.restaurant.grandmasfood.mapper.impl.util.MyUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OrderEntityToDtoMapper implements Mapper<OrderEntity, OrderDto> {

    private final ClientEntityToDtoMapper clientEntityToDtoMapper;

    private final ProductEntityToDtoMapper productEntityToDtoMapper;

    @Autowired
    public OrderEntityToDtoMapper(ClientEntityToDtoMapper clientEntityToDtoMapper, ProductEntityToDtoMapper productEntityToDtoMapper) {
        this.clientEntityToDtoMapper = clientEntityToDtoMapper;
        this.productEntityToDtoMapper = productEntityToDtoMapper;

    }




    @Override
    public OrderDto mapToDto(OrderEntity orderEntity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return OrderDto.builder()
                .uuid(orderEntity.getUuid())
                .creationDateTime(LocalDateTime.parse(orderEntity.getCreationDateTime().format(formatter)))
                .clientDocument(orderEntity.getClientEntity().getDocument())
                .productUuid(String.valueOf(orderEntity.getProductEntity().getUuid()))
                .quantity(orderEntity.getQuantity())
                .extraInformation(orderEntity.getExtraInformation())
                .subTotal(MyUtils.decimalFormat(orderEntity.getSubTotal()))
                .tax(MyUtils.decimalFormat(orderEntity.getTax()))
                .grandTotal(MyUtils.decimalFormat(orderEntity.getGrandTotal()))
                .delivered(orderEntity.getDelivered())
                .deliveredDate(orderEntity.getDeliveredDate())
                .build();
    }


    @Override
    public OrderEntity mapFromDto(OrderDto orderDto) {
        return OrderEntity.builder()
                .uuid(orderDto.getUuid())
                .creationDateTime(orderDto.getCreationDateTime())
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
