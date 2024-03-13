package com.restaurant.grandmasfood.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ProductDto {

    private UUID uuid;

    @NotBlank(message = "a product must have a fantasy name")
    @Size(max = 255)
    private String fantasyName;

    @NotNull(message = "a product must have a category")
    private String category;

    @NotBlank(message = "a product must have a description")
    @Size(max = 511)
    private String description;

    @NotNull(message = "a product must have a price")
    private Double price;

    @NotNull(message = "a product must have a available state")
    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(fantasyName, that.fantasyName) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fantasyName, category, description, price, available);
    }
}
