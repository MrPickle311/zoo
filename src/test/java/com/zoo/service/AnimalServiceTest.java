package com.zoo.service;

import com.zoo.config.ModelMapperConfiguration;
import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.validation.composite.AnimalInsertionCompositeValidator;
import com.zoo.service.validation.composite.ZoneIdValidator;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    public static final String SAMPLE_ZONE = "SampleZone";
    public static final String SAMPLE_ANIMAL_TYPE = "SampleAnimalTypeName";
    public static final String SAMPLE_ANIMAL_NAME = "SampleAnimalName";
    public static final String ANIMAL_NAME = "SampleAnimalName";

    @Spy
    private ModelMapper modelMapper;
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private AnimalTypeRepository animalTypeRepository;
    @Mock
    private ZoneRepository zoneRepository;
    @Mock
    private AnimalInsertionCompositeValidator animalInsertionCompositeValidator;
    @Mock
    private ZoneIdValidator zoneIdValidator;
    @InjectMocks
    private AnimalService underTest;

    @Nested
    class WhenGettingAnimals {

        @BeforeEach
        void setUp() {
            ModelMapperConfiguration.prepareModelMapper(modelMapper);
        }

        @Test
        void shouldGetAnimals() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            zone.setId(1);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal = new Animal();
            animal.setZone(zone);
            animal.setName(SAMPLE_ANIMAL_NAME);
            animal.setAnimalType(animalType);
            animal.setId(1);

            when(animalRepository.findByZoneId(anyInt(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimals(1, null, null, null, null)
                    .getAnimalsList();
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
        }

        @Test
        void shouldGetAnimalsWithPageable() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            zone.setId(1);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal = new Animal();
            animal.setZone(zone);
            animal.setName(SAMPLE_ANIMAL_NAME);
            animal.setAnimalType(animalType);
            animal.setId(1);

            when(animalRepository.findByZoneId(anyInt(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimals(1, 10, 1, true, "ASC")
                    .getAnimalsList();
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
        }

        @Test
        void shouldReturnEmptyList() {
            when(animalRepository.findByZoneId(anyInt(), any()))
                    .thenReturn(List.of());
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimals(1, 10, 1, true, "ASC")
                    .getAnimalsList();
            assertEquals(0, existingAnimals.size());
        }
    }

    @Nested
    class WhenGettingAnimalsByName {

        @BeforeEach
        void setUp() {
            ModelMapperConfiguration.prepareModelMapper(modelMapper);
        }

        @Test
        void shouldGetAnimalsByNameAndConvertToExternalDto() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            zone.setId(1);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal = new Animal();
            animal.setZone(zone);
            animal.setName(SAMPLE_ANIMAL_NAME);
            animal.setAnimalType(animalType);
            animal.setId(1);

            when(animalRepository.findByNameAndZoneId(anyString(), anyInt(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimalsByName(zone.getId(), ANIMAL_NAME, null, null)
                    .getAnimalsList();
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
        }

        @Test
        void shouldGetAnimalsByNameWithPageable() {
            var zone = new Zone();
            zone.setName(SAMPLE_ZONE);
            zone.setId(1);
            var animalType = new AnimalType();
            animalType.setName(SAMPLE_ANIMAL_TYPE);
            animalType.setId(1);
            animalType.setRequiredFoodPerDay(50);
            var animal = new Animal();
            animal.setZone(zone);
            animal.setName(SAMPLE_ANIMAL_NAME);
            animal.setAnimalType(animalType);
            animal.setId(1);

            when(animalRepository.findByNameAndZoneId(anyString(), anyInt(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimalsByName(1, animal.getName(), 10, 1)
                    .getAnimalsList();
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
        }

        @Test
        void shouldReturnEmptyList() {
            when(animalRepository.findByNameAndZoneId(anyString(), anyInt(), any()))
                    .thenReturn(List.of());
            List<ExistingAnimal> existingAnimals = underTest
                    .getAnimalsByName(1, SAMPLE_ANIMAL_NAME, 10, 1)
                    .getAnimalsList();
            assertEquals(0, existingAnimals.size());
        }
    }

    @Nested
    class WhenInsertingAnimals {

        @Test
        void shouldInsertAnimal() {
            var zone = new Zone();
            var animalType = new AnimalType();
            var animal = new Animal();
            animal.setAnimalType(animalType);
            animal.setZone(zone);
            animal.setName(SAMPLE_ANIMAL_NAME);
            animal.setId(1);
            when(zoneRepository.findById(anyInt()))
                    .thenReturn(Optional.of(zone));
            when(animalTypeRepository.findByNameIgnoreCaseAllIgnoreCase(anyString()))
                    .thenReturn(Optional.of(animalType));
            when(animalRepository.save(any()))
                    .thenReturn(animal);
            var animalAssigmentDto = AnimalAssigmentDto.builder()
                    .name(SAMPLE_ANIMAL_NAME)
                    .type(SAMPLE_ANIMAL_TYPE)
                    .build();
            var existingAnimal = underTest.addAnimal(1, animalAssigmentDto);

            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
        }

        @Test
        void shouldThrowIllegalArgumentExceptionAWhenInsertionBodyIsNull() {
            assertThrows(IllegalArgumentException.class, () -> underTest.addAnimal(1, null));
        }
    }
}