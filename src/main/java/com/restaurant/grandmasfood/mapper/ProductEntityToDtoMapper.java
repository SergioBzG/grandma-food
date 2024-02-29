package com.restaurant.grandmasfood.mapper;


import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.model.ProductDto;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityToDtoMapper implements Mapper<ProductEntity, ProductDto> {

    @Override
    public ProductDto mapToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .category(productEntity.getCategory())
                .available(productEntity.getAvailable())
                .build();
    }

    @Override
    public ProductEntity mapFromDto(ProductDto productDto) {
        return ProductEntity.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .available(productDto.getAvailable())
                .build();
    }
}
