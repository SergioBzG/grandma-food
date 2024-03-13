package com.restaurant.grandmasfood.utils;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.model.ProductDto;

import java.util.UUID;

public class ProductTestData {

    public static ProductEntity createProductEntityA() {
        return ProductEntity.builder()
                .id(1L)
                .uuid(UUID.randomUUID())
                .fantasyName("CHOCOLATE CAKE")
                .category(CategoryProduct.DESSERTS)
                .price(10.0)
                .description("A delicious chocolate cake")
                .available(true)
                .build();
    }

    public static ProductDto createProductDtoA() {
        return ProductDto.builder()
                .uuid(UUID.randomUUID())
                .fantasyName("CHOCOLATE CAKE")
                .category("DESSERTS")
                .price(10.0)
                .description("A delicious chocolate cake")
                .available(true)
                .build();
    }

    public static ProductEntity createProductEntityAUpdated() {
        return ProductEntity.builder()
                .uuid(UUID.randomUUID())
                .fantasyName("CHOCOLATE CAKE")
                .category(CategoryProduct.DESSERTS)
                .price(200.45)
                .description("Very testy cake")
                .available(true)
                .build();
    }

    public static ProductDto createProductDtoAUpdated() {
        return ProductDto.builder()
                .uuid(UUID.randomUUID())
                .fantasyName("CHOCOLATE CAKE")
                .category("DESSERTS")
                .price(200.45)
                .description("Very testy cake")
                .available(true)
                .build();
    }

    public static ProductEntity createProductEntityB() {
        return ProductEntity.builder()
                .id(2L)
                .uuid(UUID.randomUUID())
                .fantasyName("AMAZING COMBO")
                .category(CategoryProduct.MEATS)
                .price(1345.34)
                .description("A great dish")
                .available(true)
                .build();
    }

    public static ProductDto createProductDtoB() {
        return ProductDto.builder()
                .uuid(UUID.randomUUID())
                .fantasyName("AMAZING COMBO")
                .category("MEATS")
                .price(1345.34)
                .description("A great dish")
                .available(true)
                .build();
    }

}
