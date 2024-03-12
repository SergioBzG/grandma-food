package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {


    Optional <OrderEntity> findByUuid(UUID uuid);
}
