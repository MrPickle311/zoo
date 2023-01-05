package com.zoo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    public static void prepareModelMapper(ModelMapper modelMapper) {
    }

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        prepareModelMapper(modelMapper);
        return modelMapper;
    }
}