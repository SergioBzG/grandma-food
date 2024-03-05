package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    IClientService clientService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public void createClient(@RequestBody ClientDto clientDto) {
      clientService.createClient(clientDto);
    }

   /* @GetMapping(path = "/{document}")
    public Optional<Client> getClient(@PathVariable("document") String document) {
        return clientService.getClient(document);
    }*/

    @GetMapping(path = "/{document}")
    public Optional<ClientDto> getClient(@PathVariable("document") String document){
        return clientService.getClient(document);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{document}")
    public void updateClient(@PathVariable("document") String document, @RequestBody Client client) {
        clientService.updateClient(document,client);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{document}")
    public void deleteClient(@PathVariable("document") String document) {
        clientService.deleteClient(document);
    }
}
