package com.zoo.service.validation.simple;

import com.zoo.exception.DataValidationException;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalZoneExistenceSimpleValidator implements SimpleValidator<Integer> {

    private final AnimalRepository animalRepository;

    @Override
    public void validate(Integer zoneId) {
        if (!animalRepository.existsByAnimalType_Zone_Id(zoneId)) {
            throw new DataValidationException(ErrorCode.ZONE_NOT_FOUND);
        }
    }
}