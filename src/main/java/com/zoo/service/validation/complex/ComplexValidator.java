package com.zoo.service.validation.complex;

import com.zoo.service.validation.simple.Validator;
import lombok.Setter;

import java.util.Set;

@Setter
public abstract class ComplexValidator<T> {
    private Set<Validator<T>> validators;

    public void validate(T untilValidation) {
        validators.forEach(v -> v.validate(untilValidation));
    }
}