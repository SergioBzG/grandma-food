package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.OrderEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.repository.IOrderRepository;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
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
        OrderEntity orderEntity = new OrderEntity();
        Double tax = 0.19;

        Optional<ClientEntity> clientEntity  = clientRepository.findByDocumento(orderDto.getClientDocument());
        Optional<ProductEntity> productEntity = productRepository.findByUuid(UUID.fromString(orderDto.getProductUuid()));
        if (productEntity.isEmpty() || clientEntity.isEmpty()) {
            System.out.println("Product or Client not Found");
        } else {
            clientEntity.ifPresent(orderEntity::setClientEntity);
            productEntity.ifPresent(orderEntity::setProductEntity);
            orderEntity.setUuid(UUID.randomUUID());
            orderEntity.setQuantity(orderDto.getQuantity());
            orderEntity.setExtraInformation(orderDto.getExtraInformation());
            orderEntity.setDelivered(false);
            orderEntity.setCreationDateTime(LocalDateTime.now());
            Double price = productEntity.get().getPrice();
            int quantity = orderDto.getQuantity();
            Double subTotal = price * quantity;
            Double totalTax = subTotal * tax;
            Double grandTotal = subTotal + totalTax;

            orderEntity.setSubTotal(subTotal);
            orderEntity.setTax(totalTax);
            orderEntity.setGrandTotal(grandTotal);

        }
        OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
        return orderMapper.mapToDto(savedOrderEntity);
    }

    @Override
    public String deliverOrder() {
        return "Order delivered";
    }
}
