package com.restaurant.grandmasfood.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ClientDto {
    @NotBlank(message = "a document is required")
    @Size(max = 20)
    private String document;

    @NotBlank(message = "a name is required")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "an email is required")
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank(message = "a phone is required")
    @Size(max = 10)
    private String phone;

    @NotBlank(message = "a delivery address is required")
    @Size(max = 500)
    private String deliveryAddress;
}
