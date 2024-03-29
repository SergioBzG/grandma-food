package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ClientEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity,Long> {
    Optional<ClientEntity> findByDocument(String document);
    List<ClientEntity> findAll(Sort sort);
}
