package com.zoo.service;

import com.zoo.config.ModelMapperConfiguration;
import com.zoo.model.Zone;
import com.zoo.openapi.model.ZoneCreationDto;
import com.zoo.repository.ZoneRepository;
import com.zoo.service.validation.complex.ZoneCreationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ZoneServiceTest {

    public static final String SAMPLE_NAME = "SampleName";
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
    }
}