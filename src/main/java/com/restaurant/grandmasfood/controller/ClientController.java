package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.service.impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @PostMapping
    public String createClient() {

        return this.clientService.createClient();
    }

    @GetMapping(path = "/{document}")
    public ClientDto getClient(@PathVariable String document) {
        return clientService.findClient(document);
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
