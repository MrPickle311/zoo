package com.zoo.controller;

import com.zoo.openapi.api.AnimalsApi;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.service.AnimalService;
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
public class AnimalsController implements AnimalsApi {

    private final AnimalService animalService;

    @Override
    public ResponseEntity<Void> addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        log.info("Adding animal to zone: {}, with: {}", zoneId, animalAssigmentDto);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimals(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        log.info("Acquiring animals from zone: {}", zoneId);
        return ResponseEntity.status(HttpStatus.OK).body(animalService.getAnimals(zoneId, size, page, shouldSortByName, sortDirection));
    }

    @Override
    public ResponseEntity<List<ExistingAnimal>> getAnimalsByName(String animalName, Integer size, Integer page) {
        log.info("Acquiring animals with name: {}", animalName);
        return ResponseEntity.status(HttpStatus.OK).body(animalService.getAnimalsByName( animalName, size, page));
    }
}
