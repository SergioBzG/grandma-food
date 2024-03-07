package com.restaurant.grandmasfood.controller;


import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.model.OrderDto;
import com.restaurant.grandmasfood.service.IOrderService;
import com.restaurant.grandmasfood.service.impl.OrderServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
    public ResponseEntity updateDeliveredOrder(
          @PathVariable("uuid") String uuid,
          @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        try{
            boolean updated = orderService.updateOrderDeliveredStatus(uuid, timestamp);
            if (updated){
                return ResponseEntity.ok("ORder Delivered Update Succesfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){

        }

    }



}
