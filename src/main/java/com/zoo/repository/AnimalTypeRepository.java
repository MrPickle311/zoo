package com.zoo.repository;

import com.zoo.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalTypeRepository extends JpaRepository<AnimalType, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
