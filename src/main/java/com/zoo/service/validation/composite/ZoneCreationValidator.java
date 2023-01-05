package com.zoo.service.validation.composite;

import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.service.validation.simple.SimpleValidator;

import java.util.Set;

public class ZoneCreationValidator extends SimpleCompositeValidator<ZoneCreationDto> {

    public ZoneCreationValidator(Set<SimpleValidator<ZoneCreationDto>> simpleValidators) {
        super(simpleValidators);
    }
}