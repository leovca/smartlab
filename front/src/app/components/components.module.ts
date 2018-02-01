import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { LoaderComponent } from './loader/loader.component';
import { BrowserModule } from '@angular/platform-browser';

import { UsuariosComponent } from './usuarios/usuarios.component';
import { AmbienteComponent } from './ambiente/ambiente.component';
import { ConfiguracoesComponent } from './configuracoes/configuracoes.component';
import { SimuladorTemperaturaComponent } from './simulador/simulador.temperatura.component';
import { AirService } from './air.service';
import { RecomendacaoService } from './recomendacao.service';
import { ChartsModule } from 'ng2-charts';
@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    BrowserModule,
    ChartsModule
  ],
  declarations: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent,
    SimuladorTemperaturaComponent,
    ConfiguracoesComponent
  ],
  exports: [
    LoaderComponent,
    UsuariosComponent,
    AmbienteComponent,
    SimuladorTemperaturaComponent,
    ConfiguracoesComponent
  ],
  providers: [
    AirService,
    RecomendacaoService
  ]
})
export class ComponentsModule { }
