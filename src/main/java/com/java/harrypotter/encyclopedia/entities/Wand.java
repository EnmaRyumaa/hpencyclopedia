package com.java.harrypotter.encyclopedia.entities;

import jakarta.persistence.*;

@Embeddable
public class Wand {

    private String wood;
    private String core;
    private Integer length;
}
