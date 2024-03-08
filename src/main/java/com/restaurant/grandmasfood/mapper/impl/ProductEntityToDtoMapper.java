package com.restaurant.grandmasfood.mapper.impl;


import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToDtoMapper implements Mapper<ProductEntity, ProductDto> {

    @Override
    public ProductDto mapToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .fantasyName(productEntity.getFantasyName())
                .uuid(productEntity.getUuid())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .category(productEntity.getCategory().toString())
                .available(productEntity.getAvailable())
                .build();
    }

    @Override
    public ProductEntity mapFromDto(ProductDto productDto) {
        return ProductEntity.builder()
                .fantasyName(productDto.getFantasyName())
                .uuid(productDto.getUuid())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(CategoryProduct.valueOf(productDto.getCategory()))
                .available(productDto.getAvailable())
                .build();
    }
}
