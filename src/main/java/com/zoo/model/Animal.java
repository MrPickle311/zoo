package com.zoo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "animal")
@Getter
@Setter
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "animal_type_id")
    @NotNull
    private AnimalType animalType;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    @NotNull
    private Zone zone;
}
