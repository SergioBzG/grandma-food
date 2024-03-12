package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ClientEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity,Long>, CrudRepository<ClientEntity,Long> {
    @Query("SELECT c FROM ClientEntity c WHERE c.document = :documento")
    Optional<ClientEntity> findByDocumento(String documento);

    List<ClientEntity> findAllByOrderByDocumentAsc();

    List<ClientEntity> findAllByOrderByNameAsc();

    List<ClientEntity> findAllByOrderByDocumentDesc();

    List<ClientEntity> findAllByOrderByNameDesc();

    List<ClientEntity> findAllByOrderByDeliveryAddressAsc();

    List<ClientEntity> findAllByOrderByDeliveryAddressDesc();
    List<ClientEntity> findAll(Sort sort);
}
