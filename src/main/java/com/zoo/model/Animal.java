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

    String name;

    @ManyToOne
    @JoinColumn(name = "animal_type_id")
    private AnimalType animalType;
}
