package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.model.ClientDto;

import java.util.Optional;

public interface IClientService {
    Optional<ClientDto> getClient(String document);

    ClientDto createClient(ClientDto clientDto);

    void updateClient(String document, ClientDto clientDto);

    void deleteClient(String document);
}
