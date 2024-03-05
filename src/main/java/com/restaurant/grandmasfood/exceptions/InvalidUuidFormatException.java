package com.restaurant.grandmasfood.exceptions;

import lombok.Getter;

@Getter
public class InvalidUuidFormatException extends Exception {
    private final String code = "EP100";

    public InvalidUuidFormatException() {
        super("Invalid uuid format");
    }

}
