package com.zoo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "zones")
@Getter
@Setter
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "zone")
    private Set<Animal> animals;

    public int getCurrentAmountOfRequiredFood() {
        return getAnimals().stream().mapToInt(a -> a.getAnimalType().getRequiredFoodPerDay()).sum();
    }

    public int getAnimalsCount() {
        return getAnimals().size();
    }
}