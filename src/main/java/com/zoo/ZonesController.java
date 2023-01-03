package com.zoo;

import com.zoo.openapi.api.ZonesApi;
import com.zoo.openapi.model.Animal;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.Zone;
import com.zoo.openapi.model.ZoneCreationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ZonesController implements ZonesApi{

    @Override
    public ResponseEntity<Void> addAnimal(Integer id, AnimalAssigmentDto animalAssigmentDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> addZone(ZoneCreationDto zoneCreationDto) {
        return null;
    }

    @Override
    public ResponseEntity<List<Animal>> getAnimal(Integer id, String animalName) {
        return null;
    }

    @Override
    public ResponseEntity<List<Animal>> getAnimals(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Zone>> getZoneWhichRequiresMostFood() {
        return null;
    }

    @Override
    public ResponseEntity<List<Zone>> zoneWhereLiveLeastAnimals() {
        return null;
    }
}
