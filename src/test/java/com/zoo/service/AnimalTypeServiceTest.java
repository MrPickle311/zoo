package com.zoo.service;

import com.zoo.config.ModelMapperConfiguration;
import com.zoo.model.AnimalType;
import com.zoo.openapi.model.AnimalTypeCreationDto;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.service.validation.composite.AnimalsTypeCreationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalTypeServiceTest {

    public static final String SAMPLE_NAME = "SampleName";
    @Spy
    private ModelMapper modelMapper;

    @Mock
    private AnimalTypeRepository animalTypeRepository;

    @Mock
    private AnimalsTypeCreationValidator animalsTypeCreationValidator;

    @InjectMocks
    private AnimalTypeService underTest;

    @BeforeEach
    void setUp() {
        ModelMapperConfiguration.prepareModelMapper(modelMapper);
    }

    @Nested
    class WhenCreatingAnimalType {
        @Test
        void shouldCreateAnimalType() {
            AnimalType animalType = new AnimalType();
            animalType.setName(SAMPLE_NAME);
            animalType.setRequiredFoodPerDay(50);

            AnimalTypeCreationDto animalTypeCreationDto = AnimalTypeCreationDto.builder()
                    .name(SAMPLE_NAME)
                    .requiredFoodPerDay(50)
                    .build();

            when(animalTypeRepository.save(any()))
                    .thenReturn(animalType);
            var result = underTest.addAAnimalType(animalTypeCreationDto);
            assertEquals(animalType.getName(), result.getName());
            assertEquals(animalType.getRequiredFoodPerDay(), result.getRequiredFoodPerDay());
        }

        @Test
        void shouldThrowIllegalArgumentExceptionAWhenInsertionBodyIsNull() {
            assertThrows(IllegalArgumentException.class, () -> underTest.addAAnimalType(null));
        }
    }
}