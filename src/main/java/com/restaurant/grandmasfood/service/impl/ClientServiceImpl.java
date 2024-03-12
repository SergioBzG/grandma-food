package com.restaurant.grandmasfood.service.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.exception.AlreadyExistsException;
import com.restaurant.grandmasfood.exception.NoChangesInUpdateException;
import com.restaurant.grandmasfood.exception.NotFoundException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import com.restaurant.grandmasfood.repository.IClientRepository;
import com.restaurant.grandmasfood.service.IClientService;
import org.springframework.stereotype.Service;
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
    public ClientDto getClient(String document){
        Optional<ClientEntity> getClientEntity = clientRepository.findByDocumento(document);
        return getClientEntity.map(
                    this.clientMapper::mapToDto
            ).orElseThrow(() -> new NotFoundException(ExceptionCode.CLIENT_NOT_FOUND_CODE, "Client", "document"));
    }
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Optional<ClientEntity> existingClient = clientRepository.findByDocumento(clientDto.getDocument());
        if (existingClient.isPresent()){
            throw new AlreadyExistsException(ExceptionCode.CLIENT_ALREADY_EXISTS_CODE,"Client", "document",clientDto.getDocument());
        }

        ClientEntity clientEntity = clientMapper.mapFromDto(clientDto);
        ClientEntity clientEntitySaved = clientRepository.save(clientEntity);
        return clientMapper.mapToDto(clientEntitySaved);
    }
    @Override
    public void updateClient(String document, ClientDto clientDto){
        Optional<ClientEntity> updateClientEntity = clientRepository.findByDocumento(document);
        ClientDto updateClientDto = updateClientEntity.map(
                this.clientMapper::mapToDto
            ).orElseThrow(() -> new NotFoundException(ExceptionCode.CLIENT_NOT_FOUND_CODE, "Client", "document"));
        if (updateClientDto.equals(clientDto)){
            throw new NoChangesInUpdateException(ExceptionCode.CLIENT_NO_CHANGES_IN_UPDATE_CODE, "Client");
        }
        ClientEntity clientEntity = this.clientMapper.mapFromDto(clientDto);
        clientEntity.setId(updateClientEntity.get().getId());
        clientRepository.save(clientEntity);
    }
    @Override
    public void deleteClient(String document) {
        Optional<ClientEntity> deleteClientEntityOptional = clientRepository.findByDocumento(document);
        if (deleteClientEntityOptional.isPresent()) {
            ClientEntity deleteClientEntity = deleteClientEntityOptional.get();
            clientRepository.delete(deleteClientEntity);
        } else {
            throw new NotFoundException(ExceptionCode.CLIENT_NOT_FOUND_CODE, "Client", "document");
        }
    }
}