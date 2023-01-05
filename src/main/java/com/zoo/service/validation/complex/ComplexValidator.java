package com.zoo.service.validation.complex;

import com.zoo.service.validation.simple.SimpleValidator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public abstract class ComplexValidator<T> {
    private final Set<SimpleValidator<T>> simpleValidators;

    public void validate(T untilValidation) {
        simpleValidators.forEach(v -> v.validate(untilValidation));
    }
}