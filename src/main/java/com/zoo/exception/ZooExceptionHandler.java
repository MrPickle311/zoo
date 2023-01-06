package com.zoo.exception;

import com.zoo.openapi.model.Error;
import com.zoo.openapi.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class ZooExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public Error handleIllegalArgumentExceptionException(DataValidationException e) {
        log.warn("Data validation failed with error code: {}", e.getErrorCode());
        return Error.builder()
                .code(e.getErrorCode().toString())
                .description("Business data validation failed")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Data validation failed with error code: {}", e.getMessage());
        return Error.builder()
                .code(ErrorCode.INVALID_INPUT_DATA.toString())
                .description(e.getFieldErrors().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PrzekroczonyLimitJedzeniaException.class)
    public Error handleMethodArgumentNotValidException(PrzekroczonyLimitJedzeniaException e) {
        log.warn("Zone has not enough food: {}", e.getMessage());
        return Error.builder()
                .code(e.getErrorCode().toString())
                .description("The zone does not may contain more food")
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Error handleMethodArgumentNotValidException(ConstraintViolationException e) {
        log.warn("Passed not valid data: {}", e.getMessage());
        return Error.builder()
                .code(ErrorCode.INVALID_INPUT_DATA.toString())
                .description(e.getConstraintViolations().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoZonesPresentInZooException.class)
    public Error handleNoZonesPresentInZooException(NoZonesPresentInZooException e) {
        log.warn("Zoo does not contain any zone: {}", e.getMessage());
        return Error.builder()
                .code(e.getErrorCode().toString())
                .description("Zoo has not any zone")
                .build();
    }
}