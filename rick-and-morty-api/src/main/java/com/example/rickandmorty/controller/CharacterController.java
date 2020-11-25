package com.example.rickandmorty.controller;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.payload.CharacterDto;
import com.example.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CharacterDto>> getCharacters() {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getCharacters());
    }

    @PutMapping("/characters")
    public ResponseEntity addCharacter(@RequestBody Character character) {
        HttpStatus status = this.characterService.save(character) ?
                HttpStatus.CREATED :
                HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).build();
    }

    @DeleteMapping("/characters/{characterId}")
    public ResponseEntity deleteCharacter(@PathVariable Integer characterId) {
        HttpStatus status = this.characterService.delete(characterId) ?
                HttpStatus.OK :
                HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).build();
    }
}
