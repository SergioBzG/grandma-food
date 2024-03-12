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
    public Optional<ClientDto> getClient(String document) {
        //return clientRepository.findByDocumento(document);
        Optional<ClientEntity> dtoClient = this.clientRepository.findByDocumento(document);
        return dtoClient.map(client -> this.clientMapper.mapToDto(client));
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) {

        Optional<ClientDto> clientExist = getClient(clientDto.getDocument());

        if (clientExist.isPresent()){
            throw new AlreadyExistsException(ExceptionCode.CLIENT_ALREADY_EXISTS_CODE,"Client", "document",clientDto.getDocument());
        }
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
        ClientEntity clientEntity = this.clientRepository.findByDocumento(document).get();
        clientRepository.delete(clientEntity);
    }
}
