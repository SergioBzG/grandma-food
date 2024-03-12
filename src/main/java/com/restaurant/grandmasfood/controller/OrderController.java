package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.service.IOrderService;
import com.restaurant.grandmasfood.validator.impl.ClientValidator;
import com.restaurant.grandmasfood.validator.impl.OrderValidator;
import com.restaurant.grandmasfood.validator.impl.ProductValidator;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private final IOrderService orderService;
    private final OrderValidator orderValidator;
    private final ClientValidator clientValidator;
    private final ProductValidator productValidator;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Validated OrderDto orderDto, BindingResult errors){
        this.clientValidator.checkFormat(orderDto.getClientDocument());
        this.productValidator.checkFormat(orderDto.getProductUuid());
        this.orderValidator.checkMissingData(errors);
        return new ResponseEntity<>(this.orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{uuid}/delivered/{timestamp}")
    public ResponseEntity<OrderDto> updateDeliveredOrder(
          @PathVariable("uuid") String uuid,
          @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        this.orderValidator.checkFormat(uuid);
        OrderDto updatedorderDto = orderService.updateOrderDeliveredStatus(uuid, timestamp);
        return new ResponseEntity<>(updatedorderDto, HttpStatus.OK);
    }
}
