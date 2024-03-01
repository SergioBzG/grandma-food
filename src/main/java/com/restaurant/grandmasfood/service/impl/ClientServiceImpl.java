package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    @Override
    public String getClient() {
        return "Calypso";
    }

    @Override
    public String createClient() {
        return "Client created";
    }

    @Override
    public String updateClient() {
        return "Client updated";
    }

    @Override
    public String deleteClient() {
        return "Client deleted";
    }
}
