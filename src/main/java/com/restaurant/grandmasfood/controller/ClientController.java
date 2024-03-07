package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

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
    public void updateClient(@PathVariable("document") String document, @RequestBody ClientEntity clientEntity) {
        clientService.updateClient(document, clientEntity);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{document}")
    public void deleteClient(@PathVariable("document") String document) {
        clientService.deleteClient(document);
    }

    @GetMapping
    public List<ClientEntity> getClients(@RequestParam(defaultValue = "DOCUMENT") String orderBy,
                                         @RequestParam(defaultValue = "ASC") String direction) {
        return clientService.getClients(orderBy, direction);
    }
}
