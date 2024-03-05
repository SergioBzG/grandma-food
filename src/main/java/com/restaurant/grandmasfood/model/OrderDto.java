package com.restaurant.grandmasfood.model;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
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
    private ClientEntity clientDocument;
    private ProductEntity productUuid;
    private Integer quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private Boolean delivered;
    private LocalDate deliveredDate;

}
