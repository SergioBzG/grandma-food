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
    private final String PATTERN = "CC-\\d{6}";
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
    public void checkFormat(String pattern) {
        if (!pattern.matches(PATTERN))
            throw new InvalidOrMissingDataException(ExceptionCode.CLIENT_INVALID_OR_MISSING_DATA_CODE,
                    "Client",pattern);

    }

    public void checkNoUpdatedDocument(String documentUrl, String documentBody) {
        if(!documentUrl.equals(documentBody))
            throw new InvalidSearchingAttributeFormatException(
                    ExceptionCode.CLIENT_INVALID_ATTRIBUTE_FORMAT_CODE,
                    "Client",
                    "document"
            );
    }

}
