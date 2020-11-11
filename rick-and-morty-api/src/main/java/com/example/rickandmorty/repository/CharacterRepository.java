package com.example.rickandmorty.repository;

import com.example.rickandmorty.entities.Character;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends CrudRepository<Character, Integer> {

}

