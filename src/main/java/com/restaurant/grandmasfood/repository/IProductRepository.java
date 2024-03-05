package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByFantasyName(String fantasyName);
    Optional<ProductEntity> findByUuid(UUID uuid);
}
