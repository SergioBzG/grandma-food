package com.restaurant.grandmasfood.validator.impl;

import com.restaurant.grandmasfood.model.Dto;
import com.restaurant.grandmasfood.validator.IValidator;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator implements IValidator {
    @Override
    public boolean checkMissingData(Dto dto) {
        return false;
    }

    @Override
    public boolean checkFormat(String pattern) {
        return false;
    }
}