package com.zoo.exception;

import com.zoo.openapi.model.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PrzekroczonyLimitJedzeniaException extends RuntimeException{
    private final ErrorCode errorCode;
}
