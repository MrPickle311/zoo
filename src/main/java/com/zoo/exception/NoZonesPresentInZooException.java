package com.zoo.exception;

import com.zoo.openapi.model.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;

@RequiredArgsConstructor
@Getter
public class NoZonesPresentInZooException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 15555L;
    private final ErrorCode errorCode;
}
