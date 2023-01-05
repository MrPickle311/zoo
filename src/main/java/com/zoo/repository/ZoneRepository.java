package com.zoo.repository;

import com.zoo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {
    boolean existsByNameIgnoreCaseAllIgnoreCase(@NonNull String name);
    @Override
    Optional<Zone> findById(Integer zoneId);
}