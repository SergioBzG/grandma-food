package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class InvalidSearchingAttributeFormatException extends RuntimeException {
    private final String code;

    public InvalidSearchingAttributeFormatException(String code, String entityName, String attributeName, String attributeValue) {
        super(entityName + " " + attributeValue + " " + attributeName + " no valid");
        this.code = code;
    }
}
