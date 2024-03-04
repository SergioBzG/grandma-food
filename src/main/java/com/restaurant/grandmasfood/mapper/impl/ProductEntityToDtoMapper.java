package com.restaurant.grandmasfood.mapper.impl;


import com.restaurant.grandmasfood.entity.Product;
import com.restaurant.grandmasfood.mapper.Mapper;
import com.restaurant.grandmasfood.model.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToDtoMapper implements Mapper<Product, ProductDto> {

    @Override
    public ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .fantasyName(product.getFantasyName())
                .uuid(product.getUuid())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .available(product.getAvailable())
                .build();
    }

    @Override
    public Product mapFromDto(ProductDto productDto) {
        return Product.builder()
                .fantasyName(productDto.getFantasyName())
                .uuid(productDto.getUuid())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .available(productDto.getAvailable())
                .build();
    }
}
