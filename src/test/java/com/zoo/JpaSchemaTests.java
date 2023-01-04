package com.zoo;

import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import com.zoo.repository.ZoneRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaSchemaTests {

    @Autowired
    private ZoneRepository zoneRepository;

    @Test
    void zoneRepositoryShouldBeNotNull() {
        assertNotNull(zoneRepository);
    }

    @Nested
    class WhenManipulatingZones {

        @Test
        void shouldFindZones() {
            List<Zone> zones = zoneRepository.findAll();
            assertEquals(3, zones.size());
        }

        @Test
        void shouldContainAnimalTypes() {
            Zone zones = zoneRepository.findById(1).get();
            Set<AnimalType> animalTypes = zones.getAnimalTypes();
            assertEquals(1, animalTypes.size());
        }

        @Test
        void shouldContainAnimals() {
            Zone zones = zoneRepository.findById(1).get();
            List<AnimalType> animalTypes = zones.getAnimalTypes().stream().toList();
            Set<Animal> animals = animalTypes.get(0).getAnimals();
            assertEquals(2, animals.size());
        }

        @Test
        void shouldAddNewZoneWithoutAnimalType() {
            Zone zone = new Zone();
            zone.setName("FunnyZone");
            zoneRepository.saveAndFlush(zone);
            List<Zone> zones = zoneRepository.findAll();
            assertEquals(4, zones.size());
        }

        @Test
        void shouldThrowExceptionWhenInsertExistingZone() {
            Zone zone = new Zone();
            zone.setName("FunnyZone");
            Zone zone2 = new Zone();
            zone2.setName("FunnyZone");
            zoneRepository.saveAndFlush(zone);
            assertThrows(DataIntegrityViolationException.class, () -> zoneRepository.saveAndFlush(zone2));
        }

        @Test
        void shouldThrowExceptionWhenNameIsNull() {
            Zone zone = new Zone();
            assertThrows(DataIntegrityViolationException.class, () -> zoneRepository.saveAndFlush(zone));
        }
    }

    @Nested
    class WhenManipulatingAnimalTypes {

    }
}
