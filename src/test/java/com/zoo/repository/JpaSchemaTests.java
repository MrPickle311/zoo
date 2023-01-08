package com.zoo.repository;

import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaSchemaTests {

    public static final String SAMPLE_ZONE = "FunnyZone";
    public static final String SAMPLE_ANIMAL_NAME = "Harry";
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;

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
        void shouldAddNewZoneWithoutAnimalType() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            zoneRepository.saveAndFlush(zone);
            List<Zone> zones = zoneRepository.findAll();
            assertEquals(4, zones.size());
        }

        @Test
        void shouldThrowExceptionWhenInsertExistingZone() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            var zone2 = new Zone();
            zone2.setName(SAMPLE_ZONE);
            zoneRepository.saveAndFlush(zone);
            assertThrows(DataIntegrityViolationException.class, () -> zoneRepository.saveAndFlush(zone2));
        }

        @Test
        void shouldThrowExceptionWhenNameIsNull() {
            var zone = new Zone();
            assertThrows(ConstraintViolationException.class, () -> zoneRepository.saveAndFlush(zone));
        }
    }

    @Nested
    class WhenManipulatingAnimals {

        @Test
        void shouldGetAnimalsFromZone() {
            Zone zone = zoneRepository.findById(1).get();
            Set<Animal> animals = zone.getAnimals();
            assertEquals(2, animals.size());
        }

        @Test
        void shouldNotGetAnimalsIfZoneDoesNotExists() {
            assertTrue(zoneRepository.findById(111).isEmpty());
        }

        @Test
        void shouldGetAnimalsByZoneIdPaged() {
            Sort sort = Sort
                    .by("name")
                    .ascending();
            Pageable pageable = PageRequest.of(0, 2, sort);
            var result = animalRepository.findByZoneId(1, pageable);
            assertEquals(2, result.size());
        }

        @Test
        void shouldGetAnimalsByZoneIdNotPaged() {
            var result = animalRepository.findByZoneId(1, Pageable.unpaged());
            assertEquals(2, result.size());
        }

        @Test
        void zoneShouldExists() {
            assertTrue(animalRepository.existsByZoneId(1));
        }

        @Test
        void zoneShouldNotExists() {
            assertFalse(animalRepository.existsByZoneId(111));
        }

        @Test
        void shouldFindAnimalsByName() {
            Pageable pageable = PageRequest.of(0, 20);
            var result = animalRepository.findByName( SAMPLE_ANIMAL_NAME, pageable);
            assertEquals(1, result.size());
        }
    }

    @Nested
    class WhenManipulatingAnimalTypes{

        @Test
        void shouldAddAnimalType(){
            var animalType = new AnimalType();
            animalType.setRequiredFoodPerDay(50);
            animalType.setName("Pig");
            var result = animalTypeRepository.save(animalType);
            assertEquals(animalType.getName(), result.getName());
        }
    }
}