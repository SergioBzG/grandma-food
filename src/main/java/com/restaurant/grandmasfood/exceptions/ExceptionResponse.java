package com.restaurant.grandmasfood.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private String code;
    private LocalDate timestamp;
    private String description;
    private String exception;

}
