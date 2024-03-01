package com.restaurant.grandmasfood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private UUID uuid;

    @NotNull
    @CreationTimestamp
    private LocalDate creationDateTime;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_document")
    @NotNull(message = "a client is required")
    private Client client;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_uuid")
    @NotNull(message = "a product is required")
    private Product product;

    @NotNull(message = "a product quantity is required")
    private Integer quantity;

    @NotNull
    @NotBlank(message = "extra information is required")
    @Size(max = 511)
    private String extraInformation;

    @NotNull(message = "a subtotal is required")
    private Double subTotal;

    @NotNull(message = "a tax is required")
    private Double tax;

    @NotNull(message = "a grand total is required")
    private Double grandTotal;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private Boolean delivered;

    private LocalDate deliveredDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(creationDateTime, that.creationDateTime) && Objects.equals(client, that.client) && Objects.equals(product, that.product) && Objects.equals(quantity, that.quantity) && Objects.equals(extraInformation, that.extraInformation) && Objects.equals(subTotal, that.subTotal) && Objects.equals(tax, that.tax) && Objects.equals(grandTotal, that.grandTotal) && Objects.equals(delivered, that.delivered) && Objects.equals(deliveredDate, that.deliveredDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, creationDateTime, client, product, quantity, extraInformation, subTotal, tax, grandTotal, delivered, deliveredDate);
    }
}