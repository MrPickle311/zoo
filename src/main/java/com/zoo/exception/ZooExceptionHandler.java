package com.zoo.exception;

import com.zoo.openapi.model.Error;
import com.zoo.openapi.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ZooExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    public Error handleIllegalArgumentExceptionException(DataValidationException e) {
        log.warn("Data validation failed with error code: {}", e.getErrorCode());
        return Error.builder()
                .code(e.getErrorCode().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("Data validation failed with error code: {}", e.getMessage());
        return Error.builder()
                .code(ErrorCode.INVALID_INPUT_DATA.toString())
                .build();
    }
}