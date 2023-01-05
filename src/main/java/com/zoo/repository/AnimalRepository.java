package com.zoo.repository;

import com.zoo.model.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {
    boolean existsByAnimalType_Zone_Id(int zoneId);
    List<Animal> findByAnimalType_Zone_Id(int zoneId, Pageable pageable);
}
