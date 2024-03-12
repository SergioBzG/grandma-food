package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.model.ClientDto;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Optional<ClientDto> getClient(String document);

    ClientDto createClient(ClientDto clientDto);

    void updateClient(String document, ClientEntity clientEntity);

    void deleteClient(String document);

    List<ClientDto> getOrderedClients(String orderBy, String direction);
}
