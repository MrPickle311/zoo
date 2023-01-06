package com.zoo.service;

import com.zoo.exception.NoZonesPresentInZooException;
import com.zoo.model.Zone;
import com.zoo.openapi.model.*;
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

    public ExistingZoneFoodReport getZoneWhichRequiresMostFood() {
        return zoneRepository.findAll().stream()
                .map(z -> modelMapper.map(z, ExistingZoneFoodReport.class))
                .min((r1, r2) -> r2.getCurrentAmountOfRequiredFood().compareTo(r1.getCurrentAmountOfRequiredFood()))
                .orElseThrow(() -> new NoZonesPresentInZooException(ErrorCode.NO_ZONES_PRESENT_IN_ZOO));
    }

    public ExistingZoneAnimalsReport getZoneWhereLiveLeastAnimals() {
        return zoneRepository.findAll().stream()
                .map(z -> modelMapper.map(z, ExistingZoneAnimalsReport.class))
                .max((r1, r2) -> r2.getAnimalsCount().compareTo(r1.getAnimalsCount()))
                .orElseThrow(() -> new NoZonesPresentInZooException(ErrorCode.NO_ZONES_PRESENT_IN_ZOO));
    }
}