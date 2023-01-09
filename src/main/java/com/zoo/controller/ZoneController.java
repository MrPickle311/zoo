package com.zoo.controller;

import com.zoo.openapi.api.ZonesApi;
import com.zoo.openapi.model.*;
import com.zoo.service.AnimalService;
import com.zoo.service.AnimalTypeService;
import com.zoo.service.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class ZoneController implements ZonesApi {

    private final ZoneService zoneService;
    private final AnimalService animalService;
    private final AnimalTypeService animalTypeService;

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

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimals(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        log.info("Acquiring animals from zone: {}", zoneId);
        return ResponseEntity.status(HttpStatus.OK).body(animalService.getAnimals(zoneId, size, page, shouldSortByName, sortDirection));
    }

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimalsByName(Integer zoneId, String animalName, Integer size, Integer page) {
        log.info("Acquiring animals from zone: {} with name: {}", zoneId, animalName);
        return ResponseEntity.status(HttpStatus.OK).body(animalService.getAnimalsByName(zoneId, animalName, size, page));
    }

    @Override
    public ResponseEntity<ExistingAnimal> addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        log.info("Adding animal to zone: {}, with: {}", zoneId, animalAssigmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalService.addAnimal(zoneId, animalAssigmentDto));
    }

    @Override
    public ResponseEntity<ExistingAnimalType> addAnimalType(AnimalTypeCreationDto animalTypeCreationDto) {
        log.info("Adding animal type with: {}", animalTypeCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalTypeService.addAAnimalType(animalTypeCreationDto));
    }
}