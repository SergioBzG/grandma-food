package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.entity.Product;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.model.ProductDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.repository.IProductRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    IClientRepository clientRepository;

    @Autowired
    Mapper<Client, ClientDto> clientMapper;

    @Override
    public Optional<ClientDto> getClient(String document) {
        //return clientRepository.findByDocumento(document);
        Optional<Client> dtoClient = this.clientRepository.findByDocumento(document);
        return dtoClient.map(client -> this.clientMapper.mapToDto(client));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        clientDto.setDocument(clientDto.getDocument());
        clientDto.setName(clientDto.getName());
        clientDto.setEmail(clientDto.getEmail());
        clientDto.setPhone(clientDto.getPhone());
        clientDto.setDeliveryAddress(clientDto.getDeliveryAddress());
        Client clientSaved = this.clientRepository.save(clientMapper.mapFromDto(clientDto));
        return clientMapper.mapToDto(clientSaved);

        //clientRepository.save(clientDto);
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
        Client client = this.clientRepository.findByDocumento(document).get();
        clientRepository.delete(client);
    }
}
