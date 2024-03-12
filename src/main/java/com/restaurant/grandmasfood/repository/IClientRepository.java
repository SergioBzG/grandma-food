package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity,Long> {
    @Query("SELECT c FROM ClientEntity c WHERE c.document = :document")
    Optional<ClientEntity> findByDocumento(String document);
}
