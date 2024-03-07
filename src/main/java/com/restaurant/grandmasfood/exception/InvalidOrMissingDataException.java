package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class InvalidOrMissingDataException extends RuntimeException {
    private final String code;

    public InvalidOrMissingDataException(String code, String entityName, String attributeName) {
        super("Invalid or missing " + attributeName + " in " + entityName);
        this.code = code;
    }
}
