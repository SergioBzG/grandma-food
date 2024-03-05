package com.restaurant.grandmasfood.exceptions;

import java.util.UUID;


public class ProductDoesNotExist extends Exception {
    private final String code = "EP100";

    public ProductDoesNotExist(UUID uuid) {
        super("Product with uuid " + uuid + " does not exist");
    }

    public String getCode() {
        return code;
    }
}
