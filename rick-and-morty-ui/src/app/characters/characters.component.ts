import { Component, OnInit } from '@angular/core';
import { Character } from './character.model';
import { CharacterService } from './character.service';

@Component({
  selector: 'app-characters',
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.css']
})
export class CharactersComponent implements OnInit {

  characters: Character[];

  constructor(private characterService: CharacterService) {
  }

  ngOnInit() {
    this.characterService.findAll().subscribe(data => {
      this.characters = data;
    });
  }
}
