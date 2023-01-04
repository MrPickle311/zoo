package com.zoo.repository;

import com.zoo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    @Override
    Optional<Zone> findById(Integer integer);
}