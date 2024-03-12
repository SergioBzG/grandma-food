package com.restaurant.grandmasfood.controller.utils;

import java.util.HashMap;

public class FieldForClientFiltering {
    public static final HashMap<String, String> FIELDS_FOR_CLIENT_FILTERING = new HashMap<>();

    static {
        FIELDS_FOR_CLIENT_FILTERING.put("DOCUMENT", "document");
        FIELDS_FOR_CLIENT_FILTERING.put("NAME", "name");
        FIELDS_FOR_CLIENT_FILTERING.put("ADDRESS", "deliveryAddress");
    }

}
