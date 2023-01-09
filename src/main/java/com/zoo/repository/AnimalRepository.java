package com.zoo.repository;

import com.zoo.model.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {
    List<Animal> findByNameAndZoneId(String name, int id, Pageable pageable);
    List<Animal> findByZoneId(int zoneId, Pageable pageable);
    boolean existsByZoneId(int zoneId);
    List<Animal> findByName(String name, Pageable pageable);
}
