import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CharacterService } from '../character.service';

@Component({
  selector: 'app-character-form',
  templateUrl: './character-form.component.html',
  styleUrls: ['./character-form.component.css']
})
export class CharacterFormComponent implements OnInit {
  id: number;
  editMode = false;
  characterForm: FormGroup;

  constructor(private route: ActivatedRoute,
    private characterService: CharacterService,
    private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params: Params) => {
        this.id = +params['id'];
        this.editMode = params['id'] != null;
        this.initForm();
      }
    )
  }

  onSubmit() {
    if (this.editMode) {
      this.characterService.updateCharacter(this.id, this.characterForm.value);
    } else {
      this.characterService.addCharacter(this.characterForm.value);
    }
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  private initForm() {
    let characterName = '';
    let characterImage = '';
    let characterGender = '';
    let characterSpecies = '';
    let characterStatus = '';

    if (this.editMode) {
      const character = this.characterService.getCharacter(this.id);
      characterName = character.name;
      characterImage = character.image;
      characterGender = character.gender;
      characterSpecies = character.species;
      characterStatus = character.status;
    }

    this.characterForm = new FormGroup({
      'name': new FormControl(characterName, Validators.required),
      'image': new FormControl(characterImage, Validators.required),
      'gender': new FormControl(characterGender, Validators.required),
      'species': new FormControl(characterSpecies, Validators.required),
      'status': new FormControl(characterStatus, Validators.required)
    });
  }
}
