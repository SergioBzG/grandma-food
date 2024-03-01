package com.restaurant.grandmasfood.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ClientDto {
    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
}
