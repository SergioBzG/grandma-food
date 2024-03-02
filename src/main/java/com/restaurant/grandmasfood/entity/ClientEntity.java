package com.restaurant.grandmasfood.entity;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientEntity {
    private String document;
    private String fullName;
    private String email;
    private String cellphone;
    private String address;
}
