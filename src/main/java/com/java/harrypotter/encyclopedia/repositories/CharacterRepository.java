package com.java.harrypotter.encyclopedia.repositories;

import com.java.harrypotter.encyclopedia.entities.HpCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO Esse repository será utilizado para registrar requisições, etc.
public interface CharacterRepository extends JpaRepository<HpCharacter, Long> {
}
