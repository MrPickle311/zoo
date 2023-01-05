package com.zoo.service.validation.simple;

public interface Validator<T> {
    void validate(T untilValidation);
}