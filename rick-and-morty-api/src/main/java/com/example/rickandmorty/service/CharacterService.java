package com.example.rickandmorty.service;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.dto.CharacterList;
import com.example.rickandmorty.repository.CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

    private CharacterRepository characterRepository;

    private WebClient webClient;

    public CharacterService(CharacterRepository characterRepository, WebClient webClient) {
        this.characterRepository = characterRepository;
        this.webClient = webClient;
    }

    public List<Character> getCharacters() {
        List<Character> results = retrieveDataFromRemote();
        this.characterRepository.findAll().forEach(results::add);
        return results;
    }

    public void save(Character character) {
        character.setEditable(true);
        this.characterRepository.save(character);
    }

    public void delete(Integer characterId) {
        this.characterRepository.deleteById(characterId);
    }

    private List<Character> retrieveDataFromRemote() {
        List<Character> characterList = new ArrayList<>();

        ResponseEntity<CharacterList> response = webClient.get()
                .uri("character")
                .retrieve()
                .toEntity(CharacterList.class)
                .block();

        if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            characterList.addAll(response.getBody().getResults());
        }
        return characterList;
    }
}
