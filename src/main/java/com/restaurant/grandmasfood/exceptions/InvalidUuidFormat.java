package com.restaurant.grandmasfood.exceptions;


public class InvalidUuidFormat extends Exception {
    private final String code = "EP100";

    public InvalidUuidFormat() {
        super("Invalid uuid format");
    }
}
