package com.zoo.config;

import com.zoo.service.validation.complex.AnimalsAcquiringValidator;
import com.zoo.service.validation.complex.AnimalsTypeCreationValidator;
import com.zoo.service.validation.complex.ZoneCreationValidator;
import com.zoo.service.validation.simple.AnimalTypeNameSimpleValidator;
import com.zoo.service.validation.simple.AnimalZoneExistenceSimpleValidator;
import com.zoo.service.validation.simple.ZoneNameSimpleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ValidatorsConfiguration {

    private final ZoneNameSimpleValidator zoneNameValidator;
    private final AnimalZoneExistenceSimpleValidator animalZoneExistenceSimpleValidator;
    private final AnimalTypeNameSimpleValidator animalTypeNameSimpleValidator;

    @Bean
    public ZoneCreationValidator zoneInsertionValidator() {
        return new ZoneCreationValidator(Set.of(zoneNameValidator));
    }

    @Bean
    public AnimalsAcquiringValidator animalsAcquiringValidator() {
        return new AnimalsAcquiringValidator(Set.of(animalZoneExistenceSimpleValidator));
    }

    @Bean
    public AnimalsTypeCreationValidator animalsAssigmentValidator() {
        return new AnimalsTypeCreationValidator(Set.of(animalTypeNameSimpleValidator));
    }
}
