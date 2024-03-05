package com.restaurant.grandmasfood.mapper.impl;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityToDtoMapper implements Mapper<Client, ClientDto> {


    @Override
    public ClientDto mapToDto(Client client) {
        return ClientDto.builder()
                .document(client.getDocument())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .deliveryAddress(client.getDeliveryAddress()).build();
    }

    @Override
    public Client mapFromDto(ClientDto clientDto) {
        return Client.builder()
                .document(clientDto.getDocument())
                .name(clientDto.getName())
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .deliveryAddress(clientDto.getDeliveryAddress()).build();
    }
}
