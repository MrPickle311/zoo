package com.zoo.exception;

import com.zoo.openapi.model.Error;
import com.zoo.openapi.model.ErrorCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
@NoArgsConstructor
public class ZooExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public Error handleIllegalArgumentExceptionException(DataValidationException exception) {
        log.warn("Data validation failed with error code: {}", exception.getErrorCode());
        return Error.builder()
                .code(exception.getErrorCode())
                .description("Business data validation failed")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityException.class)
    public Error handleDataIntegrityException(DataIntegrityException exception) {
        log.warn("Attempt to violate data integrity: {}", exception.getErrorCode());
        return Error.builder()
                .code(exception.getErrorCode())
                .description("Attempt to violate data integrity")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn("Data validation failed with error code: {}", exception.getMessage());
        return Error.builder()
                .code(ErrorCode.INVALID_INPUT_DATA)
                .description(exception.getFieldErrors().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PrzekroczonyLimitJedzeniaException.class)
    public Error handleMethodArgumentNotValidException(PrzekroczonyLimitJedzeniaException exception) {
        log.warn("Zone has not enough food: {}", exception.getMessage());
        return Error.builder()
                .code(exception.getErrorCode())
                .description("The zone does not may contain more food")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Error handleMethodArgumentNotValidException(ConstraintViolationException exception) {
        log.warn("Passed not valid data: {}", exception.getMessage());
        return Error.builder()
                .code(ErrorCode.INVALID_INPUT_DATA)
                .description(exception.getConstraintViolations().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoZonesPresentInZooException.class)
    public Error handleNoZonesPresentInZooException(NoZonesPresentInZooException exception) {
        log.warn("Zoo does not contain any zone: {}", exception.getMessage());
        return Error.builder()
                .code(exception.getErrorCode())
                .description("Zoo has not any zone")
                .build();
    }
}