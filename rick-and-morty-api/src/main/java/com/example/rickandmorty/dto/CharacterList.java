package com.example.rickandmorty.dto;

import com.example.rickandmorty.entities.Character;
import lombok.Data;

import java.util.List;

@Data
public class CharacterList {

    private Info info;
    private List<Character> results;

}
