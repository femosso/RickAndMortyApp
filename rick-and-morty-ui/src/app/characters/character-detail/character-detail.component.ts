import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { faQuestionCircle, faFemale, faGenderless, faMale, IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Character } from '../character.model';
import { CharacterService } from '../character.service';

@Component({
  selector: 'app-character-detail',
  templateUrl: './character-detail.component.html',
  styleUrls: ['./character-detail.component.css']
})
export class CharacterDetailComponent implements OnInit {
  character: Character;
  id: number;
  gender: IconDefinition;

  constructor(private characterService: CharacterService,
    private route: ActivatedRoute,
    private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.character = this.characterService.getCharacter(this.id);
        this.selectGenderIcon(this.character.gender);
      }
    )
  }

  onEditCharacter() {
    this.router.navigate(['edit'], { relativeTo: this.route })
  }

  onDeleteCharacter() {
    this.characterService.removeCharacter(this.id);
    this.router.navigate(['/characters']);
  }

  private selectGenderIcon(genderString: string) {
    switch (genderString) {
      case "Male":
        this.gender = faMale;
        break;
      case "Female":
        this.gender = faFemale;
        break;
      case "Genderless":
        this.gender = faGenderless;
        break;
      default:
        this.gender = faQuestionCircle;
        break;
    }
  }
}
