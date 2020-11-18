package com.example.rickandmorty.controller;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://rick-and-morty-ui:8080", "http://localhost:4200"})
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters() {
        return characterService.getCharacters();
    }

    @PutMapping("/characters")
    public void addCharacter(@RequestBody Character character) {
        this.characterService.save(character);
    }

    @DeleteMapping("/characters/{characterId}")
    public void deleteCharacter(@PathVariable Integer characterId) {
        this.characterService.delete(characterId);
    }
}
