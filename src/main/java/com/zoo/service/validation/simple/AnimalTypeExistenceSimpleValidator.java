package com.zoo.service.validation.simple;

import com.zoo.exception.DataValidationException;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.repository.AnimalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalTypeExistenceSimpleValidator implements AnimalInsertionValidator {

    private final AnimalTypeRepository animalTypeRepository;

    @Override
    public void validate(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        if (!animalTypeRepository.existsByNameIgnoreCase(animalAssigmentDto.getType())) {
            throw new DataValidationException(ErrorCode.ANIMAL_TYPE_NOT_FOUND);
        }
    }
}