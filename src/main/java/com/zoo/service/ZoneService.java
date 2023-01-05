package com.zoo.service;

import com.zoo.model.Zone;
import com.zoo.openapi.model.ExistingZone;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.validation.composite.ZoneCreationValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final ModelMapper modelMapper;
    private final ZoneCreationValidator zoneCreationValidator;

    @Transactional
    public ExistingZone addZone(ZoneCreationDto zoneCreationDto) {
        zoneCreationValidator.validate(zoneCreationDto);
        var newZone = modelMapper.map(zoneCreationDto, Zone.class);
        return modelMapper.map(zoneRepository.save(newZone), ExistingZone.class);
    }
}
