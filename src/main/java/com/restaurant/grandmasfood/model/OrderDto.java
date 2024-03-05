package com.restaurant.grandmasfood.model;

import com.restaurant.grandmasfood.entity.Client;
import com.restaurant.grandmasfood.entity.Product;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {

    private UUID uuid;
    private LocalDate creationDateTime;
    private Client clientDocument;
    private Product productUuid;
    private Integer quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private Boolean delivered;
    private LocalDate deliveredDate;

}
