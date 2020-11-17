import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

import { Character } from './character.model';

@Injectable()
export class CharacterService {
  charactersChanged = new Subject<Character[]>();
  private characters: Character[] = [];

  constructor(private http: HttpClient) {}

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

  removeCharacter(index: number) {
    let characterId = this.characters[index].id;
    this.characters.splice(index, 1);
    this.charactersChanged.next(this.characters.slice());
    this.deleteCharacter(characterId);
  }

  storeCharacter(character: Character) {
    return this.http
      .put<Character>(
        environment.apiUrl,
        character
      )
      .subscribe(response => {
        console.log(response);
      });
  }

  fetchCharacters() {
    return this.http
      .get<Character[]>(
        environment.apiUrl
      )
      .pipe(
        tap(characters => {
          this.setCharacters(characters);
        })
      );
  }

  deleteCharacter(characterId: string) {
    return this.http
      .delete<Character>(
        environment.apiUrl + characterId
      )
      .subscribe(response => {
        console.log(response);
      });
  }
}
