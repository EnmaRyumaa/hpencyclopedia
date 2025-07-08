package com.java.harrypotter.encyclopedia.entities;

import jakarta.persistence.*;

@Embeddable
public class Wand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String wood;
    private String core;
    private Integer lenght;
}
