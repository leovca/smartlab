import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import {Usuario} from './usuario';
import 'rxjs/add/operator/map';
import {Http, Headers} from '@angular/http';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
  selector: 'usuarios',
  templateUrl: 'usuarios.component.html',
  styleUrls: ['usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  @ViewChild(ModalDirective) modal: ModalDirective;
  subscription = this._stompService.subscribe('/topic/usuarios');
  todosUsuarios: any;
  usuarios: any = [];

  messages: string[];

  constructor(
    private _stompService: StompService,
    private _http: Http) { }

  setPreference(usuario) {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    let temperatura = prompt("Insira a preferência do usuário (16º-30º)");
    if(temperatura) {
      this._http.post(`http://localhost:8733/${usuario}/vote`,
        temperatura,
        {headers: headers})
        .subscribe(data => {
        }, error => {
          console.log(JSON.stringify(error.json()));
        });
    }
  }

  ngOnInit(): void {
    this.todosUsuarios = [
      new Usuario(1, this._http),
      new Usuario(2, this._http),
      new Usuario(3, this._http),
      new Usuario(4, this._http),
    ];

    this.subscription.map((message: Message) => {
      return JSON.parse(message.body);
    }).subscribe((msg_body: any) => {
      this.usuarios = msg_body
    });
  }

  get getUsuariosMarcados() {
    return this.todosUsuarios
      .filter(opt => opt.checked)
      .map(opt => opt.value)
  }

}
