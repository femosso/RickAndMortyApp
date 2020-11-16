import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';

import { Character } from './character.model';
import { tap } from 'rxjs/operators';

@Injectable()
export class CharacterService {
  charactersChanged = new Subject<Character[]>();
  private characters: Character[] = [];
  private charactersUrl: string;

  constructor(private http: HttpClient) {
    this.charactersUrl = 'http://localhost:8080/characters';
  }

  addCharacter(character: Character) {
    character.editable = true;
    this.characters.push(character);
    this.charactersChanged.next(this.characters.slice());
    this.storeCharacter(character);
  }

  updateCharacter(index: number, newCharacter: Character) {
    // keep value of editable and id
    newCharacter.editable = this.characters[index].editable;
    newCharacter.id = this.characters[index].id;
    this.characters[index] = newCharacter;
    this.charactersChanged.next(this.characters.slice());
    this.storeCharacter(newCharacter);
  }

  getCharacters() {
    return this.characters.slice(); // return a new copy of this array
  }

  getCharacter(index: number) {
    return this.characters[index];
  }

  setCharacters(characters: Character[]) {
    this.characters = characters;
    this.charactersChanged.next(this.characters.slice());
  }

  deleteCharacter(index: number) {

  }

  storeCharacter(character: Character) {
    return this.http
      .put<Character>(
        this.charactersUrl,
        character
      )
      .subscribe(response => {
        console.log(response);
      });
  }

  fetchCharacters() {
    return this.http
      .get<Character[]>(
        this.charactersUrl
      )
      .pipe(
        tap(characters => {
          this.setCharacters(characters);
        })
      );
  }
}
