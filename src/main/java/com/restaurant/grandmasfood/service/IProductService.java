package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.model.ProductDto;

import java.util.List;

public interface IProductService {
    String createProduct();

    String getProduct();

    String updateProduct();

    String deleteProduct();

    List<ProductDto> findAll();
}
