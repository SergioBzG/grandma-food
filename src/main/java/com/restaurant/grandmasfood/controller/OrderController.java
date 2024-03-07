package com.restaurant.grandmasfood.controller;


import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.service.IOrderService;
import com.restaurant.grandmasfood.service.impl.OrderServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {


    private final IOrderService orderService;

    public OrderController(final OrderServiceImpl orderService){this.orderService = orderService;}

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) throws AlreadyExistsException {
        return new ResponseEntity<>(this.orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{uuid}/delivered/{timestamp}")
    public String updateDeliveredOrder(
          @PathVariable("uuid") String uuid,
          @PathVariable("timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate timestamp
    ) {
        return this.orderService.deliverOrder();
    }


}
