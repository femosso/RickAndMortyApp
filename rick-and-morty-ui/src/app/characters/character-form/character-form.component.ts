import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CharacterService } from '../character.service';

@Component({
  selector: 'app-character-form',
  templateUrl: './character-form.component.html',
  styleUrls: ['./character-form.component.css']
})
export class CharacterFormComponent implements OnInit {
  characterForm: FormGroup;

  constructor(private route: ActivatedRoute,
    private characterService: CharacterService,
    private router: Router) { }

  ngOnInit(): void {
    this.initForm();
  }

  onSubmit() {
    this.characterService.save(this.characterForm.value);
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  private initForm() {
    let characterName = '';
    let characterImage = '';
    let characterGender = '';

    this.characterForm = new FormGroup({
      'name': new FormControl(characterName, Validators.required),
      'image': new FormControl(characterImage, Validators.required),
      'gender': new FormControl(characterGender, Validators.required)
    });
  }
}
