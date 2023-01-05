package com.zoo.service;

import com.zoo.config.ModelMapperConfiguration;
import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.repository.AnimalRepository;
import com.zoo.service.validation.complex.AnimalsAcquiringValidator;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    public static final String SAMPLE_ZONE = "SampleZone";
    public static final String SAMPLE_ANIMAL_TYPE = ExistingAnimal.TypeEnum.ELEPHANT.toString();
    public static final String SAMPLE_ANIMAL_NAME = "SampleAnimalName";
    public static final String ANIMAL_NAME = "Harry";

    @Spy
    private ModelMapper modelMapper;
    @Mock
    private AnimalRepository animalRepository;
    @Mock
    private AnimalsAcquiringValidator animalsAcquiringValidator;
    @InjectMocks
    private AnimalService underTest;

    @Nested
    class WhenGettingAnimals {

        @BeforeEach
        void setUp() {
            ModelMapperConfiguration.prepareModelMapper(modelMapper);
        }

        @Test
        void shouldGetAnimalsAndConvertToExternalDto() {
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

            when(animalRepository.findByZone_Id(anyInt(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest.getAnimals(1, null, null, null, null);
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType().toString());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
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

            when(animalRepository.findByName(anyString(), any()))
                    .thenReturn(List.of(animal));
            List<ExistingAnimal> existingAnimals = underTest.getAnimalsByName(ANIMAL_NAME, null, null);
            assertEquals(1, existingAnimals.size());

            ExistingAnimal existingAnimal = existingAnimals.stream().findFirst().get();

            assertEquals(animal.getAnimalType().getName(), existingAnimal.getType().toString());
            assertEquals(animal.getId(), existingAnimal.getId());
            assertEquals(animal.getName(), existingAnimal.getName());
            assertEquals(animal.getZone().getName(), existingAnimal.getZone());
        }
    }
}