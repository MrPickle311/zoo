package com.zoo.service.validation.simple;

import com.zoo.exception.DataValidationException;
import com.zoo.openapi.model.AnimalTypeCreationDto;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.repository.AnimalTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalTypeNameSimpleValidator implements SimpleValidator<AnimalTypeCreationDto> {

    private final AnimalTypeRepository animalTypeRepository;

    @Override
    public void validate(AnimalTypeCreationDto untilValidation) {
        if (animalTypeRepository.existsByNameIgnoreCase(untilValidation.getName())) {
            throw new DataValidationException(ErrorCode.ALREADY_EXISTS);
        }
    }
}