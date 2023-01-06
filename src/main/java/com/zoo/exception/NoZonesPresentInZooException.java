package com.zoo.exception;

import com.zoo.openapi.model.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NoZonesPresentInZooException extends RuntimeException{
    private final ErrorCode errorCode;
}
