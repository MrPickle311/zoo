package com.zoo.service;

import com.zoo.model.Animal;
import com.zoo.openapi.model.AnimalAssigmentDto;
import com.zoo.openapi.model.ExistingAnimal;
import com.zoo.repository.AnimalRepository;
import com.zoo.service.validation.complex.AnimalsAcquiringValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalService {

    public static final String NAME = "name";
    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;
    private final AnimalsAcquiringValidator animalsAcquiringValidator;

    public List<ExistingAnimal> getAnimals(Integer zoneId, Integer size, Integer page, Boolean shouldSortByName, String sortDirection) {
        animalsAcquiringValidator.validate(zoneId);
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

    public ExistingAnimal addAnimal(Integer zoneId, AnimalAssigmentDto animalAssigmentDto) {
        return null;
    }
}