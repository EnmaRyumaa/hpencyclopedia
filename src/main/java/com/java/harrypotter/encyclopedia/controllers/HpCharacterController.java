package com.java.harrypotter.encyclopedia.controllers;

import com.java.harrypotter.encyclopedia.dto.HpCharacterDTO;
import com.java.harrypotter.encyclopedia.services.HpCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/character")
public class HpCharacterController {

    @Autowired
    private HpCharacterService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<HpCharacterDTO> getCharacterById(@PathVariable Long id) {
        HpCharacterDTO dto = new HpCharacterDTO();
        return ResponseEntity.ok(service.getCharacterById(id));
    }
}
