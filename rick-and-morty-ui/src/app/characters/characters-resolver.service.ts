import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';

import { Character } from './character.model';
import { CharacterService } from './character.service';

@Injectable({ providedIn: 'root' })
export class CharactersResolverService implements Resolve<Character[]> {
    constructor(private charactersService: CharacterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const characters = this.charactersService.getCharacters();

        if (characters.length === 0) {
            return this.charactersService.fetchCharacters();
        } else {
            return characters;
        }
    }
}