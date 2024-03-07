package com.restaurant.grandmasfood.validator;

import com.restaurant.grandmasfood.model.Dto;

public interface IValidator {
    boolean checkMissingData(Dto dto);
    boolean checkFormat(String pattern);
}
