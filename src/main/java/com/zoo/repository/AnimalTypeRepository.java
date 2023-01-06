package com.zoo.repository;

import com.zoo.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
    Optional<AnimalType> findByNameIgnoreCaseAllIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
