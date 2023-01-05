package com.zoo.service.validation;

public interface Validator<T> {
    void validate(T untilValidation);
}