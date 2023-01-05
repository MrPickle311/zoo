package com.zoo.config;

import com.zoo.service.validation.composite.CompositeAnimalInsertionValidator;
import com.zoo.service.validation.composite.ZoneIdValidator;
import com.zoo.service.validation.composite.AnimalsTypeCreationValidator;
import com.zoo.service.validation.composite.ZoneCreationValidator;
import com.zoo.service.validation.simple.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ValidatorsConfiguration {

    private final ZoneNameSimpleValidator zoneNameValidator;
    private final ZoneExistenceSimpleValidator zoneExistenceSimpleValidator;
    private final AnimalTypeNameSimpleValidator animalTypeNameSimpleValidator;
    private final FoodCapacityValidator foodCapacityValidator;
    private final AnimalTypeExistenceSimpleValidator animalTypeExistenceSimpleValidator;

    @Bean
    public ZoneCreationValidator zoneInsertionValidator() {
        return new ZoneCreationValidator(Set.of(zoneNameValidator));
    }

    @Bean
    public ZoneIdValidator animalsAcquiringValidator() {
        return new ZoneIdValidator(Set.of(zoneExistenceSimpleValidator));
    }

    @Bean
    public AnimalsTypeCreationValidator animalsAssigmentValidator() {
        return new AnimalsTypeCreationValidator(Set.of(animalTypeNameSimpleValidator));
    }

    @Bean
    public CompositeAnimalInsertionValidator animalCreationValidator() {
        return new CompositeAnimalInsertionValidator(Set.of(foodCapacityValidator, animalTypeExistenceSimpleValidator));
    }
}
