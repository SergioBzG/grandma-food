package com.restaurant.grandmasfood.service;

import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.model.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductByUuid(UUID uuid);
    void updateProduct(ProductDto productDto, UUID uuid);
    void deleteProduct(UUID uuid);
    Page<ProductDto> findAll(Pageable pageable);
    List<ProductDto> filterAllByFantasyName(String query);

    List<ProductDto> findByAvailableTrue();

}
