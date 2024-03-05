package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.model.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.document = :documento")
    Optional<Client> findByDocumento(String documento);
}
