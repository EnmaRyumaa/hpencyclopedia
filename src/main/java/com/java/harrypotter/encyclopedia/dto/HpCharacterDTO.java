package com.java.harrypotter.encyclopedia.dto;

import com.java.harrypotter.encyclopedia.entities.HpCharacter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HpCharacterDTO {

    private Long id;
    private String name;
    private String house;
    private String species;
    private String gender;
    private LocalDate birthday;

    public HpCharacterDTO(HpCharacter character) {
        this.id = character.getId();
        this.name = character.getName();
        this.house = character.getHouse();
        this.species = character.getSpecies();
        this.gender = character.getGender();
        this.birthday = character.getBirthday();
    }

}
