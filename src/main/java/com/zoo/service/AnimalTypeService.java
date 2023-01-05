package com.zoo.service;

import com.zoo.model.AnimalType;
import com.zoo.openapi.model.AnimalTypeCreationDto;
import com.zoo.openapi.model.ExistingAnimalType;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.service.validation.composite.AnimalsTypeCreationValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AnimalTypeService {

    private final AnimalTypeRepository animalTypeRepository;
    private final AnimalsTypeCreationValidator animalsTypeCreationValidator;
    private final ModelMapper modelMapper;

    @Transactional
    public ExistingAnimalType addAAnimalType(AnimalTypeCreationDto animalTypeCreationDto) {
        animalsTypeCreationValidator.validate(animalTypeCreationDto);
        var newAnimalType = modelMapper.map(animalTypeCreationDto, AnimalType.class);
        return modelMapper.map(animalTypeRepository.save(newAnimalType), ExistingAnimalType.class);
    }
}