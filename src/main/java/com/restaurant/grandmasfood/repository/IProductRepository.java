package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {

}
