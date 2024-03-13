package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long>,
        PagingAndSortingRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByFantasyName(String fantasyName);
    Optional<ProductEntity> findByUuid(UUID uuid);
    @Query("SELECT p FROM ProductEntity p WHERE p.fantasyName LIKE %?1%")
    List<ProductEntity> filterAllByFantasyName(Sort sort, String query);
}
