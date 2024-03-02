package com.restaurant.grandmasfood.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntity {

    private Long id;
    private UUID uuid;
    private Integer quantity;
    private ClientEntity client;
    private ProductEntity product;
    private String additionalInfo;
    private Double subTotal;
    private Double iva;
    private Double total;
    private Boolean delivered;
    private LocalDate dateOrdered;
    private LocalDate dateDelivered;
}
