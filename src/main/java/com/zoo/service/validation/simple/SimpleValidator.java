package com.zoo.service.validation.simple;

public interface SimpleValidator<T> {
    void validate(T untilValidation);
}