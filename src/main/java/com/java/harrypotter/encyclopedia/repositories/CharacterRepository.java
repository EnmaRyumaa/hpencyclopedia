package com.java.harrypotter.encyclopedia.repositories;

import com.java.harrypotter.encyclopedia.entities.HpCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<HpCharacter, Long> {
}
