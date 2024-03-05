package com.restaurant.grandmasfood.repository.impl;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.repository.IClientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ClientRepositoryImpl implements IClientRepository {

    List<Client> clientEntityList = null;

    @Override
    public List<Client> getClientList() {
        return null;
    }

    @Override
    public Client getClientByDocument(String document) {
        return clientEntityList.stream().filter(c -> Objects.equals(c.getDocument(), document)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init(){
        clientEntityList = List.of(
                Client.builder()
                        .document("1234")
                        .name("Dick Dickson")
                        .email("dick12@yahoo.es")
                        .phone("345665")
                        .deliveryAddress("Avenue Carlson")
                        .build(),
                Client.builder()
                        .document("8888")
                        .name("Carl Sagan")
                        .email("carlito@gmail.es")
                        .phone("98866")
                        .deliveryAddress("Avenue York")
                        .build(),
                Client.builder()
                        .document("7777")
                        .name("Juan Magan")
                        .email("juanito@yahoo.es")
                        .phone("374645")
                        .deliveryAddress("Avenue Circunvalar")
                        .build(),
                Client.builder()
                        .document("1111")
                        .name("Edgar Cuartas")
                        .email("edgarshon@tapmail.com")
                        .phone("3233455")
                        .deliveryAddress("Avenue FiveSeven")
                        .build()

        );
    }
}
