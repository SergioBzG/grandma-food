package com.restaurant.grandmasfood.repository.impl;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.entity.ProductEntity;
import com.restaurant.grandmasfood.repository.IProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements IProductRepository {


    //DUmmy = Datos quemados

    List<ProductEntity> productEntityList = null;


    @Override
    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    @Override
    public ProductEntity findProductById(Long id){
        return productEntityList.stream().filter(p-> Objects.equals(p.getId(), id)).findFirst().orElse(null);

    }

    @PostConstruct
    public void init(){
        productEntityList = List.of(
                ProductEntity.builder()
                        .id(1L)
                        .name("Hamburguesa Doble Carne")
                        .uuid(UUID.randomUUID().toString())
                        .description("Piña")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(19000.0)
                        .available(true)
                        .build(),
                ProductEntity.builder()
                        .id(2L)
                        .name("Hamburgesa con Queso")
                        .uuid(UUID.randomUUID().toString())
                        .description("Jamon")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(15000.0)
                        .available(true)
                        .build(),
                ProductEntity.builder()
                        .id(3L)
                        .name("Hot Dog")
                        .uuid(UUID.randomUUID().toString())
                        .description("Papas + Gaseosa")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(15000.0)
                        .available(true)
                        .build()
        );
    }
}
