package com.java.harrypotter.encyclopedia.services;

import com.java.harrypotter.encyclopedia.dto.HpCharacterDTO;
import com.java.harrypotter.encyclopedia.entities.HpCharacter;
import com.java.harrypotter.encyclopedia.external.config.HpApiConfiguration;
import com.java.harrypotter.encyclopedia.repositories.CharacterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HpCharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private HpApiConfiguration api;

    public HpCharacterDTO getCharacterById(Long id) {
        api.
        HpCharacter hpCharacter = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id não encontrado")
        );
        return new HpCharacterDTO(hpCharacter);
    }
}
