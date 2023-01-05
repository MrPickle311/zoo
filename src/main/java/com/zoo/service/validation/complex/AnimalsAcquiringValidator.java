package com.zoo.service.validation.complex;

import com.zoo.service.validation.simple.SimpleValidator;

import java.util.Set;

public class AnimalsAcquiringValidator extends ComplexValidator<Integer> {
    public AnimalsAcquiringValidator(Set<SimpleValidator<Integer>> simpleValidators) {
        super(simpleValidators);
    }
}
