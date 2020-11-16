import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { Character } from '../character.model';
import { CharacterService } from '../character.service';

@Component({
  selector: 'app-character-list',
  templateUrl: './character-list.component.html',
  styleUrls: ['./character-list.component.css']
})
export class CharacterListComponent implements OnInit {
  characters: Character[];
  subscription: Subscription;

  constructor(private characterService: CharacterService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscription = this.characterService.charactersChanged.subscribe(
      (characters: Character[]) => {
        this.characters = characters;
      }
    );
    this.characters = this.characterService.getCharacters();
  }

  onNewCharacter() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onSelected(index: number) {
    this.characterService.getCharacter(index);
  }
}
