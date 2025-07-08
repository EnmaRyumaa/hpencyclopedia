package com.java.harrypotter.encyclopedia.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@Table(name = "tb_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String house;
    private String species;
    private String gender;
    private LocalDate birthday;

    @Embedded
    private Wand wand;
}
