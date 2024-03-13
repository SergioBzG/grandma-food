package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.OrderEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.exception.NotFoundException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.repository.IOrderRepository;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IClientRepository clientRepository;
    private final IProductRepository productRepository;
    private final Mapper<OrderEntity, OrderDto> orderMapper;


    @Autowired
    public OrderServiceImpl(IOrderRepository orderRepository, Mapper<OrderEntity, OrderDto> orderMapper, IClientRepository clientRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        final Double TAX = 0.19;

        ClientEntity clientEntity = clientRepository.findByDocument(orderDto.getClientDocument())
                .orElseThrow(() -> new NotFoundException(
                        ExceptionCode.CLIENT_NOT_FOUND_CODE,
                        "Client",
                        "document")
                );
        ProductEntity productEntity = productRepository.findByUuid(UUID.fromString(orderDto.getProductUuid()))
                .orElseThrow(() -> new NotFoundException(ExceptionCode.PRODUCT_NOT_FOUND_CODE,
                        "Product",
                        "UUID"
                )
        );
        // Completing dto data
        orderDto.setUuid(UUID.randomUUID());
        orderDto.setDelivered(false);
        orderDto.setCreationDateTime(LocalDateTime.now());
        orderDto.setSubTotal(productEntity.getPrice()*orderDto.getQuantity());
        orderDto.setTax(productEntity.getPrice()*orderDto.getQuantity()*TAX);
        orderDto.setGrandTotal(productEntity.getPrice()*orderDto.getQuantity() * (1+TAX));
        // Create new order
        OrderEntity newOrderEntity = this.orderMapper.mapFromDto(orderDto);
        newOrderEntity.setClientEntity(clientEntity);
        newOrderEntity.setProductEntity(productEntity);

        OrderEntity savedOrderEntity = orderRepository.save(newOrderEntity);
        return orderMapper.mapToDto(savedOrderEntity);
    }

    @Transactional
    @Override
    public OrderDto updateOrderDeliveredStatus(String uuid, LocalDateTime timestamp) {
        OrderEntity orderEntity = orderRepository.findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException(
                        ExceptionCode.ORDER_NOT_FOUND_CODE,
                        "Order",
                        "UUID"
                ));
        orderEntity.setDelivered(true);
        orderEntity.setDeliveredDate(timestamp);
        return this.orderMapper.mapToDto(orderEntity);
    }

}