package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.ProductEntity;

import java.util.List;

public interface IProductRepository {
    List<ProductEntity> getProductEntityList();

    ProductEntity findProductById(Long id);
}
