package com.restaurant.grandmasfood.mapper;

import com.restaurant.grandmasfood.model.ClientDto;

public class ClientMapper {
    public ClientDto mapDtoToEntity(ClientDto clientDto){
        return clientDto.builder()
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .name(clientDto.getName())
                .document(clientDto.getDocument())
                .build();
    }
}
