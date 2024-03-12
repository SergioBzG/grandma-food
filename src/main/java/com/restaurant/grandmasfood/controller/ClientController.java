package com.restaurant.grandmasfood.controller;

import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.service.IClientService;
import com.restaurant.grandmasfood.validator.impl.ClientValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    private final IClientService clientService;
    private final ClientValidator validator;


    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody @Validated ClientDto clientDto, BindingResult errors) {
        validator.checkMissingData(errors);
        return new ResponseEntity<>(clientService.createClient(clientDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{document}")
    public Optional<ClientDto> getClient(@PathVariable("document") String document){
        validator.checkFormat(document);
        return new ResponseEntity<>(clientService.getClient(document), HttpStatus.OK).getBody();
    }

    @PutMapping(path = "/{document}")
    public ResponseEntity<?> updateClient(@PathVariable("document") String document, @RequestBody @Validated ClientDto clientDto, BindingResult errors) {
        validator.checkFormat(document);
        validator.checkNoUpdatedDocument(document,clientDto.getDocument());
        validator.checkMissingData(errors);
        clientService.updateClient(document, clientDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{document}")
    public ResponseEntity<?> deleteClient(@PathVariable("document") String document, @Validated ClientDto clientDto,BindingResult errors) {
        validator.checkFormat(document);
        clientService.deleteClient(document);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
