package com.example.rickandmorty.dto;

import lombok.Data;

@Data
class Info {

    private int count;
    private int pages;
    private String next;
    private String prev;

}
