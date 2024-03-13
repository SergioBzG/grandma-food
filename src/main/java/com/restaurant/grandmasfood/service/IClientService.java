package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.model.ClientDto;

import java.util.List;


public interface IClientService {
    ClientDto getClient(String document);

    ClientDto createClient(ClientDto clientDto);

    void updateClient(String document, ClientDto clientDto);

    void deleteClient(String document);

    List<ClientDto> getOrderedClients(String orderBy, String direction);
}