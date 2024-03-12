package com.restaurant.grandmasfood.exception;

import com.restaurant.grandmasfood.exception.utils.ExceptionCode;
import com.restaurant.grandmasfood.exception.utils.ExceptionResponse;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;


@RestControllerAdvice
public class ExceptionHandlerAdvicer {
    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> alreadyExistsExceptionHandler(AlreadyExistsException alreadyExistsException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(alreadyExistsException.getCode())
                .description(alreadyExistsException.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(alreadyExistsException.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({InvalidOrMissingDataException.class})
    public ResponseEntity<ExceptionResponse> invalidOrMissingDataExceptionHandler(InvalidOrMissingDataException invalidOrMissingDataException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(invalidOrMissingDataException.getCode())
                .description(invalidOrMissingDataException.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(invalidOrMissingDataException.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }@ExceptionHandler({InvalidSearchingAttributeFormatException.class})
    public ResponseEntity<ExceptionResponse> invalidSearchingAttributeFormatExceptionHandler(InvalidSearchingAttributeFormatException invalidSearchingAttributeFormatException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(invalidSearchingAttributeFormatException.getCode())
                .description(invalidSearchingAttributeFormatException.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(invalidSearchingAttributeFormatException.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({NoChangesInUpdateException.class})
    public ResponseEntity<ExceptionResponse> noChangesInUpdateExceptionHandler(NoChangesInUpdateException noChangesInUpdateException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(noChangesInUpdateException.getCode())
                .description(noChangesInUpdateException.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(noChangesInUpdateException.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionResponse> notFoundExceptionHandler(NotFoundException notFoundException) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(notFoundException.getCode())
                .description(notFoundException.getMessage())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(notFoundException.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception exception) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(ExceptionCode.FATAL_ERROR)
                .description(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .exception(Arrays.toString(exception.getStackTrace()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

