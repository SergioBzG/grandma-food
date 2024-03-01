package com.restaurant.grandmasfood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    @NotNull
    @NotBlank(message = "a document is required")
    @Size(max = 20)
    private String document;

    @NotNull
    @NotBlank(message = "a name is required")
    @Size(max = 255)
    private String name;

    @NotNull
    @NotBlank(message = "an email is required")
    @Size(max = 255)
    private String email;

    @NotNull
    @NotBlank(message = "a phone is required")
    @Size(max = 10)
    private String phone;

    @NotNull
    @NotBlank(message = "a delivery address is required")
    @Size(max = 500)
    private String deliveryAddress;

    @OneToMany(mappedBy = "client")
    private Set<OrderEntity> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(Id, that.Id) && Objects.equals(document, that.document) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(deliveryAddress, that.deliveryAddress) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, document, name, email, phone, deliveryAddress, orders);
    }
}


