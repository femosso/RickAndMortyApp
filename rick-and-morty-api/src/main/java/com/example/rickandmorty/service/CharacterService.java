package com.example.rickandmorty.service;

import com.example.rickandmorty.entities.Character;
import com.example.rickandmorty.payload.CharacterDto;
import com.example.rickandmorty.payload.CharacterListDto;
import com.example.rickandmorty.repository.CharacterRepository;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

    public CharacterService(CharacterRepository characterRepository, WebClient webClient, ModelMapper modelMapper) {
        this.characterRepository = characterRepository;
        this.webClient = webClient;
        this.modelMapper = modelMapper;
    }

    public List<CharacterDto> getCharacters() {
        List<CharacterDto> results = retrieveDataFromRemote();
        this.characterRepository.findAll().forEach(character -> results.add(convertToDto(character)));
        return results;
    }

    public boolean save(Character character) {
        character.setEditable(true);
        Character result = this.characterRepository.save(character);
        return this.characterRepository.findById(result.getId()).isPresent();
    }

    public boolean delete(Integer characterId) {
        this.characterRepository.deleteById(characterId);
        return this.characterRepository.findById(characterId).isEmpty();
    }

    private CharacterDto convertToDto(Character character) {
        return modelMapper.map(character, CharacterDto.class);
    }

    private List<CharacterDto> retrieveDataFromRemote() {
        List<CharacterDto> characterList = new ArrayList<>();

        ResponseEntity<CharacterListDto> response = webClient.get()
                .uri("/character")
                .retrieve()
                .toEntity(CharacterListDto.class)
                .block();

        if (response != null && response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            characterList.addAll(response.getBody().getResults());
        }
        return characterList;
    }
}
