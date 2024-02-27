package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    IClientService clientService;

    @PostMapping
    public String createClient() {
        return clientService.createClient();
    }

    @GetMapping(path = "/{document}")
    public String getClient(@PathVariable("document") String document) {
        return clientService.getClient();
    }

    @PutMapping(path = "/{document}")
    public String updateClient(@PathVariable("document") String document) {
        return clientService.updateClient();
    }

    @DeleteMapping(path = "/{document}")
    public String deleteClient(@PathVariable("document") String document) {
        return clientService.deleteClient();
    }
}
