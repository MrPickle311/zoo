package com.zoo.config;

import com.zoo.service.validation.complex.ZoneCreationValidator;
import com.zoo.service.validation.simple.ZoneNameSimpleValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class ValidatorsConfiguration {

    private final ZoneNameSimpleValidator zoneNameValidator;

    @Bean
    public ZoneCreationValidator zoneInsertionValidator() {
        return new ZoneCreationValidator(Set.of(zoneNameValidator));
    }
}
