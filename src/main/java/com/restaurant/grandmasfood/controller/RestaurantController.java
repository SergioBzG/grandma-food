package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/clients")
public class RestaurantController {

    @Autowired
    IRestaurantService restaurantService;

    @GetMapping(path = "/{document}")
    public String getRestaurant(@PathVariable("document") String document) {
        return restaurantService.findAllClients();
    }

}
