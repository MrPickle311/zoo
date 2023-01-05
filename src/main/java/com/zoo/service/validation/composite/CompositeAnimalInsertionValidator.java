package com.zoo.service.validation.composite;

import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.service.validation.simple.AnimalInsertionValidator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class CompositeAnimalInsertionValidator implements AnimalInsertionValidator {

    private final Set<AnimalInsertionValidator> animalInsertionValidators;

    @Override
    public void validate(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        animalInsertionValidators.forEach(v -> v.validate(zoneId, animalAssigmentDto));
    }
}