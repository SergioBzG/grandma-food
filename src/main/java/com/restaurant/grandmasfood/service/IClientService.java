package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.model.ClientDto;

import java.util.List;

public interface IClientService {
    String getClient();

    String createClient();

    String updateClient();

    String deleteClient();

    List<ClientDto> findAllClients();


}
