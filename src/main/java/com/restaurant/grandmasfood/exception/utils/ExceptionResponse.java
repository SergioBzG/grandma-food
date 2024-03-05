package com.restaurant.grandmasfood.exception.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;

}
