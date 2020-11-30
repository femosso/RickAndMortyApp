package com.example.rickandmorty.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {

    private int id;
    private String name;
    private String gender;
    private String image;
    private String species;
    private String status;
    private boolean editable;

}
