package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class InvalidOrMissingDataException extends RuntimeException {
    private final String code;

    public InvalidOrMissingDataException(String code, String entityName) {
        super("Invalid or missing required " + entityName + " data");
        this.code = code;
    }
}
