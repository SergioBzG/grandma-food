package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.model.ClientDto;

import java.util.Optional;

public interface IClientService {
    Optional<Client> getClient(String document);

    void createClient(Client client);

    void updateClient(String document, Client client);

    void deleteClient(String document);
}
