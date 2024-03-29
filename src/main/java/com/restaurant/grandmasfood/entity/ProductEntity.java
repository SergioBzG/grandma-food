package com.restaurant.grandmasfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID uuid;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "a product should has a fantasy name")
    @Size(max = 255)
    private String fantasyName;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private CategoryProduct category;

    @NotNull
    @NotBlank(message = "a description is required")
    @Size(max = 511)
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Boolean available;

    @OneToMany(mappedBy = "productEntity")
    private Set<OrderEntity> orderEntities;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(fantasyName, that.fantasyName) && category == that.category && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(available, that.available) && Objects.equals(orderEntities, that.orderEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, fantasyName, category, description, price, available, orderEntities);
    }


}
