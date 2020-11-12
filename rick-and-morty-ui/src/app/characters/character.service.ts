import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';

import { Character } from './character.model';

@Injectable()
export class CharacterService {
  charactersChanged = new Subject<Character[]>();
  private characters: Character[] = [];

  private charactersUrl: string;

  constructor(private http: HttpClient) {
    this.charactersUrl = 'http://localhost:8080/characters';
  }

  public findAll(): Observable<Character[]> {
    return this.http
      .get<Character[]>(
        this.charactersUrl
      )
      .pipe(
        tap(characters => {
          this.characters = characters;
        })
      );
  }

  public save(character: Character) {
    console.log("sending character: " + character);
    return this.http
      .put<Character>(
        this.charactersUrl,
        character
      )
      .subscribe(response => {
        console.log(response);
      });
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

}
