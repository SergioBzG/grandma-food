package com.restaurant.grandmasfood.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ClientDto {
    @NotBlank(message = "a document is required")
    @Size(max = 20)
    @Pattern(regexp = "CC-\\d{6}", message = "document format invalid")
    private String document;

    @NotBlank(message = "a name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "an email is required")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "email format invalid")
    @Size(max = 255)
    private String email;

    @NotBlank(message = "a phone is required")
    @Size(max = 10)
    private String phone;

    @NotBlank(message = "a delivery address is required")
    @Size(max = 500)
    private String deliveryAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(document, clientDto.document) && Objects.equals(name, clientDto.name) && Objects.equals(email, clientDto.email) && Objects.equals(phone, clientDto.phone) && Objects.equals(deliveryAddress, clientDto.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, name, email, phone, deliveryAddress);
    }
}
