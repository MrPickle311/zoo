package com.zoo.service.validation.simple;

import com.zoo.openapi.model.AnimalAssigmentDto;

public interface AnimalInsertionValidator {
    void validate(Integer zoneId, AnimalAssigmentDto animalAssigmentDto);
}
