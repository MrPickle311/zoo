package com.zoo.config;

import com.zoo.service.validation.composite.AnimalInsertionCompositeValidator;
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

    private final ZoneNameExistenceSimpleValidator zoneNameValidator;
    private final ZoneExistenceSimpleValidator zoneExistenceSimpleValidator;
    private final AnimalTypeNameExistenceSimpleValidator animalTypeNameExistenceSimpleValidator;
    private final FoodCapacityValidator foodCapacityValidator;

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
        return new AnimalsTypeCreationValidator(Set.of(animalTypeNameExistenceSimpleValidator));
    }

    @Bean
    public AnimalInsertionCompositeValidator animalCreationValidator() {
        return new AnimalInsertionCompositeValidator(Set.of(foodCapacityValidator));
    }
}
