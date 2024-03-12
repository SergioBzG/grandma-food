package com.restaurant.grandmasfood.validator.impl;

import com.restaurant.grandmasfood.entity.CategoryProduct;
import com.restaurant.grandmasfood.exception.InvalidOrMissingDataException;
import com.restaurant.grandmasfood.exception.InvalidSearchingAttributeFormatException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.validator.IValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ProductValidator implements IValidator {

    private static final String PATTERN = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
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
    public void checkFormat(String attribute) {
        if(!attribute.matches(PATTERN))
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Product",
                    "UUID",
                    attribute
            );
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

    public void checkNoUpdatedUuid(String uuidInUrl, String uuidInBody) {
        if(!uuidInUrl.equals(uuidInBody))
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Product",
                    "UUID",
                    uuidInBody
            );
    }

    public void checkQueryParamForProductFiltering(String query) {
        if(query == null || query.trim().isEmpty())
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.PRODUCT_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Product",
                    "query param for filtering",
                    query
            );
    }
}
