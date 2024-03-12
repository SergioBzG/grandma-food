package com.restaurant.grandmasfood.exception;

import lombok.Getter;

@Getter
public class NoChangesInUpdateException extends RuntimeException {
    private final String code;

    public NoChangesInUpdateException(String code, String entityName) {
        super("There is no changes in " + entityName);
        this.code = code;
    }
}
