package com.restaurant.grandmasfood.model;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {

    private String name;
    private CategoryProduct category;
    private String description;
    private Double price;
    private Boolean available;

}
