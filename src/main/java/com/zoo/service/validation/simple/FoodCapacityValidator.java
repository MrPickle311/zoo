package com.zoo.service.validation.simple;

import com.zoo.exception.DataValidationException;
import com.zoo.exception.PrzekroczonyLimitJedzeniaException;
import com.zoo.model.Zone;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodCapacityValidator implements AnimalInsertionValidator {

    @Value("${com.max-food-per-day}")
    private Integer foodLimit;
    private final ZoneRepository zoneRepository;
    private final AnimalTypeRepository animalTypeRepository;

    @Override
    public void validate(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        if (isFoodLimitReached(zoneId, animalAssigmentDto)) {
            throw new PrzekroczonyLimitJedzeniaException(ErrorCode.NO_MORE_FOOD);
        }
    }

    private boolean isFoodLimitReached(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        return (sumCurrentFoodRequirementsInZone(zoneId) +
                getRequiredFoodPerDayForNewAnimal(animalAssigmentDto)) > foodLimit;
    }

    private int getRequiredFoodPerDayForNewAnimal(AnimalAssigmentDto animalAssigmentDto) {
        return animalTypeRepository.findByNameIgnoreCaseAllIgnoreCase(animalAssigmentDto.getType()).
                orElseThrow(() -> new DataValidationException(ErrorCode.ANIMAL_TYPE_NOT_FOUND))
                .getRequiredFoodPerDay();
    }

    private int sumCurrentFoodRequirementsInZone(int zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new DataValidationException(ErrorCode.ZONE_NOT_FOUND));
        return zone.getAnimals().stream().mapToInt(a -> a.getAnimalType().getRequiredFoodPerDay()).sum();
    }
}