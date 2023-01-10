package com.zoo.service;

import com.zoo.exception.NotFoundException;
import com.zoo.model.Animal;
import com.zoo.model.AnimalType;
import com.zoo.model.Zone;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ErrorCode;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.openapi.model.ExistingAnimalsList;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.AnimalTypeRepository;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.util.PageableConfigurator;
import com.zoo.service.validation.composite.AnimalInsertionCompositeValidator;
import com.zoo.service.validation.composite.ZoneIdValidator;
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
    private final AnimalInsertionCompositeValidator animalInsertionCompositeValidator;

    public ExistingAnimalsList getAnimals(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        zoneIdValidator.validate(zoneId);
        List<Animal> animalList = findAnimalsByZoneId(zoneId, size, page, shouldSortByName, sortDirection);
        return convertModelToDto(animalList);
    }

    private List<Animal> findAnimalsByZoneId(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        Pageable pageable = PageableConfigurator.preparePageable(size, page, Boolean.TRUE.equals(shouldSortByName) ? NAME : null, sortDirection);
        return animalRepository.findByZoneId(zoneId, pageable);
    }

    public ExistingAnimalsList getAnimalsByName(Integer zoneId, String animalName, Integer size, Integer page) {
        zoneIdValidator.validate(zoneId);
        Pageable pageable = PageableConfigurator.preparePageable(size, page, null, null);
        List<Animal> animalList = animalRepository.findByNameAndZoneId(animalName, zoneId, pageable);
        return convertModelToDto(animalList);
    }

    @Transactional
    public ExistingAnimal addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        animalInsertionCompositeValidator.validate(zoneId, animalAssigmentDto);
        var animal = convertDtoToModel(animalAssigmentDto);
        animal.setZone(findZoneById(zoneId));
        animal.setAnimalType(findAnimalTypeByName(animalAssigmentDto.getType()));
        return save(animal);
    }

    private ExistingAnimalsList convertModelToDto(List<Animal> animalList) {
        var convertedList = animalList.stream().map(a -> modelMapper.map(a, ExistingAnimal.class)).toList();
        return ExistingAnimalsList.builder()
                .animalsList(convertedList)
                .build();
    }
    private Animal convertDtoToModel(AnimalAssigmentDto animalAssigmentDto) {
        return modelMapper.map(animalAssigmentDto, Animal.class);
    }

    private ExistingAnimal save(Animal animal) {
        return modelMapper.map(animalRepository.save(animal), ExistingAnimal.class);
    }

    private AnimalType findAnimalTypeByName(String animalTypeName) {
        return animalTypeRepository.findByNameIgnoreCaseAllIgnoreCase(animalTypeName)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ANIMAL_TYPE_NOT_FOUND));
    }

    private Zone findZoneById(Integer zoneId) {
        return zoneRepository.findById(zoneId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.ZONE_NOT_FOUND));
    }
}