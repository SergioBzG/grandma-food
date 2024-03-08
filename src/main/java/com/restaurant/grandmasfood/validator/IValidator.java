package com.restaurant.grandmasfood.validator;

import org.springframework.validation.BindingResult;

public interface IValidator {
    void checkMissingData(BindingResult errors);
    void checkFormat(String pattern);
}
