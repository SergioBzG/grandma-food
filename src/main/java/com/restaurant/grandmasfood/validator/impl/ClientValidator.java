package com.restaurant.grandmasfood.validator.impl;

import com.restaurant.grandmasfood.exception.InvalidOrMissingDataException;
import com.restaurant.grandmasfood.exception.InvalidSearchingAttributeFormatException;
import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.validator.IValidator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.Objects;

@Primary
@Component
public class ClientValidator implements IValidator {
    private static final String PATTERN = "^(CC|CE|P)-\\d{3,17}$";
    @Override
    public void checkMissingData(BindingResult errors) {
        if (errors.hasErrors()){
            throw new InvalidOrMissingDataException(
                    ExceptionCode.CLIENT_INVALID_OR_MISSING_DATA_CODE,
                    "Client",
                    Objects.requireNonNull(errors.getFieldError()).getField()
            );
        }
    }
    @Override
    public void checkFormat(String attribute) {
        if (!attribute.matches(PATTERN))
            throw new InvalidOrMissingDataException(ExceptionCode.CLIENT_INVALID_OR_MISSING_DATA_CODE,
                    "Client",attribute);

    }

    public void checkNoUpdatedDocument(String documentUrl, String documentBody) {
        if(!documentUrl.equals(documentBody))
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.CLIENT_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Client",
                    "document",
                    documentBody
            );
    }

}
