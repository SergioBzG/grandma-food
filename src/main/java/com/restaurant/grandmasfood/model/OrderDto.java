package com.restaurant.grandmasfood.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {

    private UUID uuid;
    private LocalDateTime creationDateTime;

    @NotNull(message = "a client is required")
    private String clientDocument;

    @NotNull(message = "a product is required")
    private String productUuid;

    @NotNull(message = "a product quantity is required")
    @Range(min=1, max=99)
    private Integer quantity;

    @NotNull
    @Size(max = 511)
    private String extraInformation;

    private Double subTotal;
    private Double tax;
    private Double grandTotal;
    private Boolean delivered;
    private LocalDateTime deliveredDate;

}
