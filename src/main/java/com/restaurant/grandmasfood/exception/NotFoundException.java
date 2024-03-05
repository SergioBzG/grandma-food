package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception {
    private final String code;

    public NotFoundException(String code, String entityName, String attributeName) {
        super("Not found " + entityName + " with this " + attributeName);
        this.code = code;
    }
}
