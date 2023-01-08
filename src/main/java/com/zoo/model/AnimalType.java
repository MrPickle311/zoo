package com.zoo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "animal_type")
@Getter
@Setter
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Column(name = "required_food_per_day")
    private int requiredFoodPerDay;

    @OneToMany(mappedBy = "animalType")
    private Set<Animal> animals;
}