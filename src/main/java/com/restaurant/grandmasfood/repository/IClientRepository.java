package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ClientEntity;

import java.util.List;

public interface IClientRepository {
    List<ClientEntity> getClientEntityList();

    ClientEntity getClientByDocument(String document);
}
