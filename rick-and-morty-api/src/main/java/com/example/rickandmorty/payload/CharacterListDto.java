package com.example.rickandmorty.payload;

import lombok.Data;

import java.util.List;

@Data
public class CharacterListDto {

    private InfoDto info;
    private List<CharacterDto> results;

}
