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

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final ModelMapper modelMapper;
    private final ZoneCreationValidator zoneCreationValidator;

    @Transactional
    public ExistingZone addZone(ZoneCreationDto zoneCreationDto) {
        zoneCreationValidator.validate(zoneCreationDto);
        var newZone = convertDtoToModel(zoneCreationDto);
        return save(newZone);
    }

    public ExistingZoneFoodReport getZoneWhichRequiresMostFood() {
        return zoneRepository.findAll().stream()
                .map(z -> modelMapper.map(z, ExistingZoneFoodReport.class))
                .max(Comparator.comparing(ExistingZoneFoodReport::getCurrentAmountOfRequiredFood))
                .orElseThrow(() -> new NoZonesPresentInZooException(ErrorCode.NO_ZONES_PRESENT_IN_ZOO));
    }

    public ExistingZoneAnimalsReport getZoneWhereLiveLeastAnimals() {
        return zoneRepository.findAll().stream()
                .map(z -> modelMapper.map(z, ExistingZoneAnimalsReport.class))
                .min(Comparator.comparing(ExistingZoneAnimalsReport::getAnimalsCount))
                .orElseThrow(() -> new NoZonesPresentInZooException(ErrorCode.NO_ZONES_PRESENT_IN_ZOO));
    }

    private ExistingZone save(Zone newZone) {
        return modelMapper.map(zoneRepository.save(newZone), ExistingZone.class);
    }

    private Zone convertDtoToModel(ZoneCreationDto zoneCreationDto) {
        return modelMapper.map(zoneCreationDto, Zone.class);
    }
}