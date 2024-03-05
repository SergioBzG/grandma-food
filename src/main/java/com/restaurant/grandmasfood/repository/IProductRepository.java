package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, Long> {

    boolean existsByFantasyName(String fantasyName);
    Optional<Product> findByUuid(UUID uuid);
}
