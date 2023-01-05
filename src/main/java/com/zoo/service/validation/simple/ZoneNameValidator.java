package com.zoo.service.validation.simple;

import com.zoo.exception.DataValidationException;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneNameValidator implements Validator<ZoneCreationDto> {

    private final ZoneRepository zoneRepository;

    @Override
    public void validate(ZoneCreationDto untilValidation) {
        if (zoneRepository.existsByNameIgnoreCaseAllIgnoreCase(untilValidation.getName())) {
            throw new DataValidationException(ErrorCode.ALREADY_EXISTS);
        }
    }
}