import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { CharactersComponent } from './characters/characters.component';
import { CharacterDetailComponent } from './characters/character-detail/character-detail.component';
import { CharacterService } from './characters/character.service';
import { CharacterFormComponent } from './characters/character-form/character-form.component';
import { CharacterListComponent } from './characters/character-list/character-list.component';
import { CharacterItemComponent } from './characters/character-list/character-item/character-item.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CharactersComponent,
    CharacterDetailComponent,
    CharacterFormComponent,
    CharacterListComponent,
    CharacterItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CharacterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
