package com.restaurant.grandmasfood.model;

import com.restaurant.grandmasfood.entity.ClientEntity;
import com.restaurant.grandmasfood.entity.ProductEntity;
import jakarta.validation.Valid;
import lombok.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {

    private UUID uuid;
    private LocalDateTime creationDateTime;
    private String clientDocument;
    private String productUuid;
    private Integer quantity;
    private String extraInformation;
    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private Boolean delivered;
    private LocalDateTime deliveredDate;


}
