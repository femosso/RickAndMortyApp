import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CharacterDetailComponent } from './characters/character-detail/character-detail.component';
import { CharacterFormComponent } from './characters/character-form/character-form.component';
import { CharactersResolverService } from './characters/characters-resolver.service';
import { CharactersComponent } from './characters/characters.component';

const routes: Routes = [
  { path: '', redirectTo: '/characters', pathMatch: 'full' }, // only redirect if "full path" is empty
  {
    path: 'characters',
    component: CharactersComponent,
    children: [
      { path: 'new', component: CharacterFormComponent },
      { path: ':id', component: CharacterDetailComponent, resolve: [CharactersResolverService] },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
