package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository = null;

    @Override
    public Optional<Client> getClient(String document) {
        return clientRepository.findByDocumento(document);
    }

    @Override
    public void createClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(String document, Client client){
        Client updateClient = clientRepository.findByDocumento(document).get();
        updateClient.setName(client.getName());
        updateClient.setEmail(client.getEmail());
        updateClient.setPhone(client.getPhone());
        updateClient.setDeliveryAddress(client.getDeliveryAddress());
        clientRepository.save(updateClient);
    }

    @Override
    public void deleteClient(String document) {
        Client client = clientRepository.findByDocumento(document).get();
        clientRepository.delete(client);
    }
}
