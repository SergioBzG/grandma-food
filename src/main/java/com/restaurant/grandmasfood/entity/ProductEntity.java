package com.restaurant.grandmasfood.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {

    private Long id;
    private String uuid;
    private String name;
    private CategoryProduct category;
    private String description;
    private Double price;
    private Boolean available;

}
