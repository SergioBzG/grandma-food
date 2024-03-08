package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.model.ProductDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductByUuid(UUID uuid);
    void updateProduct(ProductDto productDto, UUID uuid);
    void deleteProduct(UUID uuid);
    List<ProductDto> findAll();
    Optional<ProductDto> findByFantasyName(String fantasyName);
}
