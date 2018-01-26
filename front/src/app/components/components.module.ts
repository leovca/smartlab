import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { LoaderComponent } from './loader/loader.component';


import { UsuariosComponent } from './usuarios/usuarios.component';
import { AmbienteComponent } from './ambiente/ambiente.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
  ],
  declarations: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent
  ],
  exports: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent
  ]
})
export class ComponentsModule { }
