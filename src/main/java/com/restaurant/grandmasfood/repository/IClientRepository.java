package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.Client;

import java.util.List;

public interface IClientRepository {
    List<Client> getClientList();

    Client getClientByDocument(String document);
}
