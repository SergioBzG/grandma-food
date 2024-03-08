package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.repository.IOrderRepository;
import com.restaurant.grandmasfood.service.IOrderService;
import com.restaurant.grandmasfood.service.impl.OrderServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {


    private final IOrderService orderService;
    private final IOrderRepository orderRepository;

    public OrderController(final OrderServiceImpl orderService, IOrderRepository orderRepository){this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) throws AlreadyExistsException {
        return new ResponseEntity<>(this.orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{uuid}/delivered/{timestamp}")
    public ResponseEntity updateDeliveredOrder(
          @PathVariable("uuid") String uuid,
          @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        try{
            String correctUUid = String.valueOf(UUID.fromString(uuid));
            boolean updated = orderService.updateOrderDeliveredStatus(correctUUid, timestamp);
            if (updated){
                OrderDto orderDto = orderService.getOrderByUuid(uuid);
                return ResponseEntity.ok(orderDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }
}
