package com.zoo.controller;

import com.zoo.openapi.api.ZonesApi;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.openapi.model.ExistingZone;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ZonesController implements ZonesApi {

    private final ZoneService zoneService;

    @Override
    public ResponseEntity<Void> addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        log.info("Adding animal to zone: {}, with: {}", zoneId, animalAssigmentDto);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<ExistingZone> addZone(ZoneCreationDto zoneCreationDto) {
        log.info("Adding zone with: {}", zoneCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(zoneService.addZone(zoneCreationDto));
    }

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimal(Integer zoneId, String animalName) {
        log.info("Acquiring animal from zone: {}, with name: {}", zoneId, animalName);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimals(Integer zoneId) {
        log.info("Acquiring animals from zone: {}", zoneId);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<List<ExistingZone>> getZoneWhichRequiresMostFood() {
        log.info("Acquiring zone which requires most food");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<List<ExistingZone>> zoneWhereLiveLeastAnimals() {
        log.info("Acquiring zone where live least animals");
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
