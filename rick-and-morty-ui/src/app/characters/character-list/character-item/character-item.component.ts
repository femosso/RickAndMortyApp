import { Component, OnInit, Input } from '@angular/core';
import { Character } from '../../character.model';

@Component({
  selector: 'app-character-item',
  templateUrl: './character-item.component.html',
  styleUrls: ['./character-item.component.css']
})
export class CharacterItemComponent implements OnInit {
  @Input() character: Character
  @Input() index: number

  constructor() { }

  ngOnInit(): void {
  }

}
