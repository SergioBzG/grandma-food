package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.model.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);

    String getProduct();

    String updateProduct();

    String deleteProduct();

    List<ProductDto> findAll();
}
