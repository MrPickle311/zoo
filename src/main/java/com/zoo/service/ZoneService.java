package com.zoo.service;

import com.zoo.model.Zone;
import com.zoo.openapi.model.ExistingZone;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.validation.ready.ZoneCreationValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final ModelMapper modelMapper;

    private final ZoneCreationValidator zoneCreationValidator;

    public ExistingZone addZone(ZoneCreationDto zoneCreationDto) {
        zoneCreationValidator.validate(zoneCreationDto);
        var newZone = modelMapper.map(zoneCreationDto, Zone.class);
        return modelMapper.map(zoneRepository.save(newZone), ExistingZone.class);
    }
}
