package com.restaurant.grandmasfood.exceptions;

import lombok.Getter;

import java.util.UUID;


@Getter
public class ProductDoesNotExistException extends Exception {
    private final String code = "EP100";

    public ProductDoesNotExistException(UUID uuid) {
        super("Product with uuid " + uuid + " does not exist");
    }

}
