package com.restaurant.grandmasfood.mapper;


import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.model.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityToDtoMapper implements Mapper<ClientEntity, ClientDto> {


    @Override
    public ClientDto mapToDto(ClientEntity clientEntity) {
        return ClientDto.builder()
                .document(clientEntity.getDocument())
                .fullName(clientEntity.getFullName())
                .address(clientEntity.getAddress())
                .email(clientEntity.getEmail())
                .cellphone(clientEntity.getCellphone())
                .build();
    }

    @Override
    public ClientEntity mapFromDto(ClientDto clientDto) {
        return ClientEntity.builder()
                .document(clientDto.getDocument())
                .fullName(clientDto.getFullName())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .cellphone(clientDto.getCellphone())
                .build();
    }
}
