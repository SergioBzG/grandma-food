package com.restaurant.grandmasfood.model;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ProductDto {

    private UUID uuid;
    private String fantasyName;
    private CategoryProduct category;
    private String description;
    private Double price;
    private Boolean available;

}
