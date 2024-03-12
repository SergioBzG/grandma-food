package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.controller.utils.FieldForClientFiltering;
import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<ClientDto> getOrderedClients(String orderBy, String direction) {
        if(!FieldForClientFiltering.FIELDS_FOR_CLIENT_FILTERING.containsKey(orderBy))
            return clientRepository.findAll().stream()
                    .map(this.clientMapper::mapToDto)
                    .toList();

        if(direction.equals("ASC"))
            return this.clientRepository.findAll(Sort.by(
                    Sort.Direction.ASC, FieldForClientFiltering.FIELDS_FOR_CLIENT_FILTERING.get(orderBy))
                    ).stream()
                    .map(this.clientMapper::mapToDto)
                    .toList();
        return this.clientRepository.findAll(Sort.by(
                Sort.Direction.DESC, FieldForClientFiltering.FIELDS_FOR_CLIENT_FILTERING.get(orderBy))
                ).stream()
                .map(this.clientMapper::mapToDto)
                .toList();

    }
}
