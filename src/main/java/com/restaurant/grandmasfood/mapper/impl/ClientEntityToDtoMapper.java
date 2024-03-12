package com.restaurant.grandmasfood.mapper.impl;


import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityToDtoMapper implements Mapper<ClientEntity, ClientDto> {
    @Override
    public ClientDto mapToDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .document(clientEntity.getDocument())
                .name(clientEntity.getName())
                .email(clientEntity.getEmail())
                .phone(clientEntity.getPhone())
                .deliveryAddress(clientEntity.getDeliveryAddress())
                .build();
    }
    @Override
    public ClientEntity mapFromDto(ClientDto clientDto) {
        return ClientEntity.builder()
                .document(clientDto.getDocument())
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .deliveryAddress(clientDto.getDeliveryAddress())
                .build();
    }
}
