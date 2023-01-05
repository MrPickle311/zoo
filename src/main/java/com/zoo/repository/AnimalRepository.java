package com.zoo.repository;

import com.zoo.model.Animal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends PagingAndSortingRepository<Animal, Integer> {
    List<Animal> findByZone_Id(int id, Pageable pageable);
    boolean existsByZone_Id(int id);
    List<Animal> findByName(String name, Pageable pageable);
}
