package com.restaurant.grandmasfood.model;

import lombok.*;

import java.time.LocalDate;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Integer quantity;
    private String additionalInfo;
    private Double subTotal;
    private Double iva;
    private Double total;
    private Boolean delivered;
    private LocalDate dateOrdered;
    private LocalDate dateDelivered;

}
