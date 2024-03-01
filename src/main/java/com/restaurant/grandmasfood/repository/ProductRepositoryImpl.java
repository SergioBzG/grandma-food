package com.restaurant.grandmasfood.repository;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.entity.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements IProductRepository {


    //DUmmy = Datos quemados

    List<Product> productList = null;


    @Override
    public List<Product> getProductEntityList() {
        return productList;
    }

    @Override
    public Product findProductById(Long id){
        return productList.stream().filter(p-> Objects.equals(p.getId(), id)).findFirst().orElse(null);

    }

    @PostConstruct
    public void init(){
        productList = List.of(
                Product.builder()
                        .id(1L)
                        .fantasyName("Hamburguesa Doble Carne")
                        .uuid(UUID.randomUUID())
                        .description("Piña")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(19000.0)
                        .available(true)
                        .build(),
                Product.builder()
                        .id(2L)
                        .fantasyName("Hamburgesa con Queso")
                        .uuid(UUID.randomUUID())
                        .description("Jamon")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(15000.0)
                        .available(true)
                        .build(),
                Product.builder()
                        .id(3L)
                        .fantasyName("Hot Dog")
                        .uuid(UUID.randomUUID())
                        .description("Papas + Gaseosa")
                        .category(CategoryProduct.HAMBURGUERS_AND_HOTDOGS)
                        .price(15000.0)
                        .available(true)
                        .build()
        );
    }
}
