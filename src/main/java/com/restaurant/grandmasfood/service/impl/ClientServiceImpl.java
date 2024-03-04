package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    IClientRepository clientRepository = null;

    Mapper<Client, ClientDto> clientMapper;


    public ClientServiceImpl(IClientRepository clientRepository, Mapper<Client, ClientDto> clientMapper){

        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }


    public ClientDto findClient(String document) {
        return clientMapper.mapToDto(clientRepository.getClientByDocument(document));
    }

    @Override
    public String getClient(){ return "Get Client";}

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

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository.getClientList().stream().map(clientMapper::mapToDto).toList();
    }
}
