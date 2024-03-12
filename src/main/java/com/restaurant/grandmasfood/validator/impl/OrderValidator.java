package com.restaurant.grandmasfood.validator.impl;

import com.restaurant.grandmasfood.exception.InvalidOrMissingDataException;
import com.restaurant.grandmasfood.exception.InvalidSearchingAttributeFormatException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.validator.IValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Component
public class OrderValidator implements IValidator {
    private static final String PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
    @Override
    public void checkMissingData(BindingResult errors) {
        if(errors.hasErrors())
            throw new InvalidOrMissingDataException(
                    ExceptionCode.ORDER_INVALID_OR_MISSING_DATA_CODE,
                    "Order",
                    Objects.requireNonNull(errors.getFieldError()).getField()
            );
    }

    @Override
    public void checkFormat(String attribute) {
        if(!attribute.matches(PATTERN))
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.ORDER_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Order",
                    "UUID",
                    attribute
            );
    }
}
