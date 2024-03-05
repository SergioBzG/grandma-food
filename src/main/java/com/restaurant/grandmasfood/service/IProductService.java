package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.exceptions.ProductDoesNotExist;
import com.restaurant.grandmasfood.model.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductByUuid(UUID uuid) throws ProductDoesNotExist;
    String updateProduct();
    void deleteProduct(UUID uuid) throws ProductDoesNotExist;
    List<ProductDto> findAll();

    boolean existsByFantasyName(String fantasyName);
}
