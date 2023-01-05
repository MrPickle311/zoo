package com.zoo.config;

import com.zoo.model.Animal;
import com.zoo.openapi.model.ExistingAnimal;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ModelMapperConfiguration {

    private static Converter<Animal, ExistingAnimal> animalExistingAnimalConverter() {
        return new AbstractConverter<>() {
            @Override
            protected ExistingAnimal convert(Animal source) {
                if (Objects.isNull(source)) {
                    return null;
                }
                return ExistingAnimal.builder()
                        .name(source.getName())
                        .id(source.getId())
                        .type(ExistingAnimal.TypeEnum.fromValue(source.getAnimalType().getName()))
                        .zone(source.getZone().getName())
                        .build();
            }
        };
    }

    public static void prepareModelMapper(ModelMapper modelMapper) {
        modelMapper.addConverter(animalExistingAnimalConverter());
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        prepareModelMapper(modelMapper);
        return modelMapper;
    }
}