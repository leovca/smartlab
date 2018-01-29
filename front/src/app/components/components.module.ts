import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { LoaderComponent } from './loader/loader.component';
import { BrowserModule } from '@angular/platform-browser';

import { UsuariosComponent } from './usuarios/usuarios.component';
import { AmbienteComponent } from './ambiente/ambiente.component';
import { SimuladorTemperaturaComponent } from './simulador/simulador.temperatura.component';
import { AirService } from './air.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    BrowserModule
  ],
  declarations: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent,
    SimuladorTemperaturaComponent
  ],
  exports: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent,
    SimuladorTemperaturaComponent
  ],
  providers: [
    AirService
  ]
})
export class ComponentsModule { }
