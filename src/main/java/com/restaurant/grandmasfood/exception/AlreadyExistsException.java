package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends Exception {
    private final String code;

    public AlreadyExistsException(String code, String entityName, String attributeName, String attributeValue) {
        super(entityName + " with " + attributeName + " " + attributeValue + " already exists");
        this.code = code;
    }
}
