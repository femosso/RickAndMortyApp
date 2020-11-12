import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { exhaustMap, map, take, tap } from 'rxjs/operators';
import { CharacterService } from './characters/character.service';
import { Character } from './characters/character.model';

@Injectable({ providedIn: 'root' })
export class DataStorageService {
    private charactersUrl: string;

    constructor(private http: HttpClient,
        private characterService: CharacterService) {
        this.charactersUrl = 'http://localhost:8080/characters';
    }

    // storeRecipes() {
    //     const recipes = this.recipeService.getRecipes();
    //     this.http
    //         .put(
    //             'https://effective-forge-147121.firebaseio.com/recipes.json',
    //              recipes
    //         )
    //         .subscribe(response => {
    //             console.log(response);
    //         });
    // }

    fetchCharacters() {
        return this.http
            .get<Character[]>(
                this.charactersUrl
            )
            .pipe(
                tap(characters => {
                    this.characterService.setCharacters(characters);
                })
            );
    }
}