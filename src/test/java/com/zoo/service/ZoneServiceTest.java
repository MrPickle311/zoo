package com.zoo.service;

import com.zoo.config.ModelMapperConfiguration;
import com.zoo.exception.NoZonesPresentInZooException;
import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.validation.composite.ZoneCreationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneServiceTest {

    public static final String SAMPLE_NAME = "SampleName";
    private static final String SAMPLE_ZONE = "SampleZone";
    private static final String SAMPLE_ANIMAL_TYPE = "SampleAnimalType";
    private static final String SAMPLE_ANIMAL_NAME = "SampleAnimalName";
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private ZoneCreationValidator zoneCreationValidator;

    @InjectMocks
    private ZoneService underTest;

    @BeforeEach
    void setUp() {
        ModelMapperConfiguration.prepareModelMapper(modelMapper);
    }

    @Nested
    class WhenCreatingZone {
        @Test
        void shouldCreateZone() {
            var zoneCreationDto = ZoneCreationDto.builder()
                    .name(SAMPLE_NAME)
                    .build();
            var zone = new Zone();
            zone.setName(SAMPLE_NAME);
            when(zoneRepository.save(any()))
                    .thenReturn(zone);
            var result = underTest.addZone(zoneCreationDto);
            assertEquals(SAMPLE_NAME, result.getName());
        }

        @Test
        void shouldThrowIllegalArgumentExceptionAWhenInsertionBodyIsNull() {
            assertThrows(IllegalArgumentException.class, () -> underTest.addZone(null));
        }
    }

    @Nested
    class WhenGettingFoodReport {

        @Test
        void shouldReturnZoneWhichRequiresMostFood() {
            var zone1 = new Zone();
            zone1.setName(SAMPLE_ZONE);
            zone1.setId(1);
            var zone2 = new Zone();
            zone2.setName(SAMPLE_ZONE);
            zone2.setId(2);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal1 = new Animal();
            animal1.setZone(zone1);
            animal1.setName(SAMPLE_ANIMAL_NAME);
            animal1.setAnimalType(animalType);
            animal1.setId(1);
            var animal2 = new Animal();
            animal2.setZone(zone1);
            animal2.setName(SAMPLE_ANIMAL_NAME);
            animal2.setAnimalType(animalType);
            animal2.setId(2);

            zone1.setAnimals(Set.of(animal1));
            zone2.setAnimals(Set.of(animal1, animal2));

            when(zoneRepository.findAll())
                    .thenReturn(List.of(zone1, zone2));
            var result = underTest.getZoneWhichRequiresMostFood();
            assertEquals(zone2.getName(), result.getName());
            assertEquals(zone2.getId(), result.getId());
        }

        @Test
        void shouldThrowNoZonesPresentInZooExceptionWhenZoneListIsEmpty() {
            when(zoneRepository.findAll())
                    .thenReturn(List.of());
            assertThrows(NoZonesPresentInZooException.class, () -> underTest.getZoneWhichRequiresMostFood());
        }
    }

    @Nested
    class WhenGettingAnimalsCountReport {

        @Test
        void shouldReturnZoneWhereLiveLeastAnimals() {
            var zone1 = new Zone();
            zone1.setName(SAMPLE_ZONE);
            zone1.setId(1);
            var zone2 = new Zone();
            zone2.setName(SAMPLE_ZONE);
            zone2.setId(2);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal1 = new Animal();
            animal1.setZone(zone1);
            animal1.setName(SAMPLE_ANIMAL_NAME);
            animal1.setAnimalType(animalType);
            animal1.setId(1);
            var animal2 = new Animal();
            animal2.setZone(zone1);
            animal2.setName(SAMPLE_ANIMAL_NAME);
            animal2.setAnimalType(animalType);
            animal2.setId(2);

            zone1.setAnimals(Set.of(animal1));
            zone2.setAnimals(Set.of(animal1, animal2));

            when(zoneRepository.findAll())
                    .thenReturn(List.of(zone1, zone2));
            var result = underTest.getZoneWhereLiveLeastAnimals();
            assertEquals(zone1.getName(), result.getName());
            assertEquals(zone1.getId(), result.getId());
        }

        @Test
        void shouldThrowNoZonesPresentInZooExceptionWhenZoneListIsEmpty() {
            when(zoneRepository.findAll())
                    .thenReturn(List.of());
            assertThrows(NoZonesPresentInZooException.class, () -> underTest.getZoneWhereLiveLeastAnimals());
        }
    }
}