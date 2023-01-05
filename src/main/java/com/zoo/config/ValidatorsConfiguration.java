package com.zoo.config;

import com.zoo.service.validation.complex.ZoneCreationValidator;
import com.zoo.service.validation.simple.ZoneNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ValidatorsConfiguration {

    private final ZoneNameValidator zoneNameValidator;

    @Bean
    public ZoneCreationValidator zoneInsertionValidator() {
        var validator = new ZoneCreationValidator();
        validator.setValidators(Set.of(zoneNameValidator));
        return validator;
    }
}
