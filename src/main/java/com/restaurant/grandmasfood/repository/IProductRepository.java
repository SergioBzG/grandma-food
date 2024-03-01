package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProductEntityList();

    Product findProductById(Long id);
}
