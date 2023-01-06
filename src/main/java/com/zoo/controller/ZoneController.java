package com.zoo.controller;

import com.zoo.openapi.api.ZonesApi;
import com.zoo.openapi.model.ExistingZone;
import com.zoo.openapi.model.ExistingZoneAnimalsReport;
import com.zoo.openapi.model.ExistingZoneFoodReport;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class ZoneController implements ZonesApi {

    private final ZoneService zoneService;

    @Override
    public ResponseEntity<ExistingZone> addZone(ZoneCreationDto zoneCreationDto) {
        log.info("Adding zone with: {}", zoneCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(zoneService.addZone(zoneCreationDto));
    }

    @Override
    public ResponseEntity<ExistingZoneFoodReport> getZoneWhichRequiresMostFood() {
        log.info("Acquiring zone which requires most food");
        return ResponseEntity.status(HttpStatus.OK).body(zoneService.getZoneWhichRequiresMostFood());
    }

    @Override
    public ResponseEntity<ExistingZoneAnimalsReport> zoneWhereLiveLeastAnimals() {
        log.info("Acquiring zone where live least animals");
        return ResponseEntity.status(HttpStatus.OK).body(zoneService.getZoneWhereLiveLeastAnimals());
    }
}