package com.restaurant.grandmasfood.validator.impl;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.exception.InvalidOrMissingDataException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.validator.IValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ProductValidator implements IValidator {
    @Override
    public void checkMissingData(BindingResult errors) {
        if(errors.hasErrors())
            throw new InvalidOrMissingDataException(
                    ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                    "Product",
                    Objects.requireNonNull(errors.getFieldError()).getField()
            );
    }

    @Override
    public void checkFormat(String pattern) {

    }

    public void checkCategory(String category) {
        boolean nonexistence = Arrays.stream(CategoryProduct.values())
                .noneMatch(c -> c.name().equals(category.toUpperCase()));
        if(nonexistence)
            throw new InvalidOrMissingDataException(
                    ExceptionCode.PRODUCT_INVALID_OR_MISSING_DATA_CODE,
                    "Product",
                    "category"
            );
    }
}
