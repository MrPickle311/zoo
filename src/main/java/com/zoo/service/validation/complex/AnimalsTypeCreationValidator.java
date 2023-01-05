package com.zoo.service.validation.complex;

import com.zoo.openapi.model.AnimalTypeCreationDto;
import com.zoo.service.validation.simple.SimpleValidator;

import java.util.Set;

public class AnimalsTypeCreationValidator extends SimpleCompositeValidator<AnimalTypeCreationDto> {
    public AnimalsTypeCreationValidator(Set<SimpleValidator<AnimalTypeCreationDto>> simpleValidators) {
        super(simpleValidators);
    }
}
