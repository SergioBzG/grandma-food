package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class InvalidSearchingAttributeFormatException extends Exception {
    private final String code;

    public InvalidSearchingAttributeFormatException(String code, String entityName, String attributeName) {
        super(entityName + " " + attributeName + " no valid");
        this.code = code;
    }
}
