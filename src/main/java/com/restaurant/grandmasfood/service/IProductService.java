package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.exception.ProductDoesNotExistException;
import com.restaurant.grandmasfood.model.ProductDto;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductByUuid(UUID uuid);
    String updateProduct();
    void deleteProduct(UUID uuid) throws ProductDoesNotExistException;
    List<ProductDto> findAll();
    boolean existsByFantasyName(String fantasyName);
}
