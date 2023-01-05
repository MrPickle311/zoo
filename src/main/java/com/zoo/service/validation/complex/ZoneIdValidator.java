package com.zoo.service.validation.complex;

import com.zoo.service.validation.simple.SimpleValidator;

import java.util.Set;

public class ZoneIdValidator extends SimpleCompositeValidator<Integer> {
    public ZoneIdValidator(Set<SimpleValidator<Integer>> simpleValidators) {
        super(simpleValidators);
    }
}
