package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    private final Mapper<ClientEntity, ClientDto> clientMapper;

    public ClientServiceImpl(IClientRepository clientRepository, Mapper<ClientEntity, ClientDto> clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Optional<ClientDto> getClient(String document) {
        //return clientRepository.findByDocumento(document);
        Optional<ClientEntity> dtoClient = this.clientRepository.findByDocumento(document);
        return dtoClient.map(client -> this.clientMapper.mapToDto(client));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        clientDto.setDocument(clientDto.getDocument());
        clientDto.setName(clientDto.getName());
        clientDto.setEmail(clientDto.getEmail());
        clientDto.setPhone(clientDto.getPhone());
        clientDto.setDeliveryAddress(clientDto.getDeliveryAddress());
        ClientEntity clientEntitySaved = this.clientRepository.save(clientMapper.mapFromDto(clientDto));
        return clientMapper.mapToDto(clientEntitySaved);

        //clientRepository.save(clientDto);
    }

    @Override
    public void updateClient(String document, ClientEntity clientEntity){
        ClientEntity updateClientEntity = clientRepository.findByDocumento(document).get();
        updateClientEntity.setName(clientEntity.getName());
        updateClientEntity.setEmail(clientEntity.getEmail());
        updateClientEntity.setPhone(clientEntity.getPhone());
        updateClientEntity.setDeliveryAddress(clientEntity.getDeliveryAddress());
        clientRepository.save(updateClientEntity);
    }

    @Override
    public void deleteClient(String document) {
        ClientEntity clientEntity = this.clientRepository.findByDocumento(document).get();
        clientRepository.delete(clientEntity);
    }

    public List<ClientEntity> getClients(String orderBy, String direction) {
        if ("DOCUMENT".equalsIgnoreCase(orderBy)) {
            if ("ASC".equalsIgnoreCase(direction)) {
                return clientRepository.findAllByOrderByDocumentAsc();
            } else {
                return clientRepository.findAllByOrderByDocumentDesc();
            }
        } else if ("NAME".equalsIgnoreCase(orderBy)) {
            if ("ASC".equalsIgnoreCase(direction)) {
                return clientRepository.findAllByOrderByNameAsc();
            } else {
                return clientRepository.findAllByOrderByNameDesc();
            }
        } else if ("ADDRESS".equalsIgnoreCase(orderBy)) {
            if ("ASC".equalsIgnoreCase(direction)) {
                return clientRepository.findAllByOrderByDeliveryAddressAsc();
            } else {
                return clientRepository.findAllByOrderByDeliveryAddressDesc();
            }
        } else {
            // Si no se proporciona un orderBy válido, se devuelve una lista sin ordenar
            return clientRepository.findAll();
        }
    }
}
