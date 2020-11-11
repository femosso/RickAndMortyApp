package com.example.rickandmorty.controller;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public List<Character> getCharacters() {
        return characterService.getCharacters();
    }

    @PostMapping("/users")
    public void addCharacter(@RequestBody Character character) {
        this.characterService.save(character);
    }
}
