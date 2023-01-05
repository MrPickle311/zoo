package com.zoo.service;

import com.zoo.model.Animal;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.util.PageableConfigurator;
import com.zoo.service.validation.complex.CompositeAnimalInsertionValidator;
import com.zoo.service.validation.complex.ZoneIdValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    public static final String NAME = "name";
    private final AnimalRepository animalRepository;
    private final ZoneRepository zoneRepository;
    private final AnimalTypeRepository animalTypeRepository;
    private final ModelMapper modelMapper;
    private final ZoneIdValidator zoneIdValidator;
    private final CompositeAnimalInsertionValidator compositeAnimalInsertionValidator;

    public List<ExistingAnimal> getAnimals(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        zoneIdValidator.validate(zoneId);
        Pageable pageable = PageableConfigurator.preparePageable(size, page, Boolean.TRUE.equals(shouldSortByName) ? NAME : null, sortDirection);
        List<Animal> animalList = animalRepository.findByZone_Id(zoneId, pageable);
        return convertAnimalsDtoToExistingAnimalsDto(animalList);
    }

    public List<ExistingAnimal> getAnimalsByName(String animalName, Integer size, Integer page) {
        Pageable pageable = PageableConfigurator.preparePageable(size, page, null, null);
        List<Animal> animalList = animalRepository.findByName(animalName, pageable);
        return convertAnimalsDtoToExistingAnimalsDto(animalList);
    }

    private List<ExistingAnimal> convertAnimalsDtoToExistingAnimalsDto(List<Animal> animalList) {
        return animalList.stream().map(a -> modelMapper.map(a, ExistingAnimal.class)).toList();
    }

    @Transactional
    public ExistingAnimal addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        zoneIdValidator.validate(zoneId);
        compositeAnimalInsertionValidator.validate(zoneId, animalAssigmentDto);
        var animal = modelMapper.map(animalAssigmentDto, Animal.class);
        animal.setZone(zoneRepository.findById(zoneId).get());
        animal.setAnimalType(animalTypeRepository.findByNameIgnoreCaseAllIgnoreCase(animalAssigmentDto.getType()).get());
        return modelMapper.map(animalRepository.save(animal), ExistingAnimal.class);
    }
}