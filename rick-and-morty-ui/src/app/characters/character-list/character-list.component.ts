import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Character } from '../character.model';
import { CharacterService } from '../character.service';

@Component({
  selector: 'app-character-list',
  templateUrl: './character-list.component.html'
})
export class CharacterListComponent implements OnInit {

  characters: Character[];

  constructor(private characterService: CharacterService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.characterService.findAll().subscribe(data => {
      this.characters = data;
    });
  }

  onNewCharacter() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }
}
