package com.restaurant.grandmasfood.controller;


import com.restaurant.grandmasfood.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping
    public String createOrder() {
        return this.orderService.createOrder();
    }

    @PatchMapping(path = "/{uuid}/delivered/{timestamp}")
    public String updateDeliveredOrder(
          @PathVariable("uuid") String uuid,
          @PathVariable("timestamp") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate timestamp
    ) {
        return this.orderService.deliverOrder();
    }
}
