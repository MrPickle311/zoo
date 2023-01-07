package com.zoo.exception;

import com.zoo.openapi.model.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;

@RequiredArgsConstructor
@Getter
public class PrzekroczonyLimitJedzeniaException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 889911L;
    private final ErrorCode errorCode;
}
