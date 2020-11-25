package com.example.rickandmorty.payload;

import lombok.Data;

@Data
class InfoDto {

    private int count;
    private int pages;
    private String next;
    private String prev;

}
