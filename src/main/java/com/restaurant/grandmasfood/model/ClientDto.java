package com.restaurant.grandmasfood.model;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class ClientDto {
    private String document;
    private String fullName;
    private String email;
    private String cellphone;
    private String address;
}
