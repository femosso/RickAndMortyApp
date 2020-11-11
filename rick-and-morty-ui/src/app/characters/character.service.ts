import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Character } from './character.model';

@Injectable()
export class CharacterService {

  private charactersUrl: string;

  constructor(private http: HttpClient) {
    this.charactersUrl = 'http://localhost:8080/characters';
  }
 
  public findAll(): Observable<Character[]> {
    return this.http.get<Character[]>(this.charactersUrl);
  }
 
  public save(character: Character) {
    return this.http.post<Character>(this.charactersUrl, character);
  }
}
