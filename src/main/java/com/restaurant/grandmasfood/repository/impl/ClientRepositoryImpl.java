package com.restaurant.grandmasfood.repository.impl;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.repository.IClientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ClientRepositoryImpl implements IClientRepository {


    List<ClientEntity> clientEntityList = null;


    @Override
    public List<ClientEntity> getClientEntityList() {
        return clientEntityList;
    }

    @Override
    public ClientEntity getClientByDocument(String document) {
        return clientEntityList.stream().filter(c -> Objects.equals(c.getDocument(), document)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init(){
        clientEntityList = List.of(
                ClientEntity.builder()
                        .document("1234")
                        .fullName("Dick Dickson")
                        .email("dick12@yahoo.es")
                        .cellphone("345665")
                        .address("Avenue Carlson")
                        .build(),
                ClientEntity.builder()
                        .document("8888")
                        .fullName("Carl Sagan")
                        .email("carlito@gmail.es")
                        .cellphone("98866")
                        .address("Avenue York")
                        .build(),
                ClientEntity.builder()
                        .document("7777")
                        .fullName("Juan Magan")
                        .email("juanito@yahoo.es")
                        .cellphone("374645")
                        .address("Avenue Circunvalar")
                        .build(),
                ClientEntity.builder()
                        .document("1111")
                        .fullName("Edgar Cuartas")
                        .email("edgarshon@tapmail.com")
                        .cellphone("3233455")
                        .address("Avenue FiveSeven")
                        .build()

        );
    }
}
