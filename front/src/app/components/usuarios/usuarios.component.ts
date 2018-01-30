import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import {Usuario} from './usuario';
import 'rxjs/add/operator/map';
import {Http, Headers} from '@angular/http';
import { Chart } from './chart';
import {RecomendacaoService} from "../recomendacao.service";

@Component({
  selector: 'usuarios',
  templateUrl: 'usuarios.component.html',
  styleUrls: ['usuarios.component.css']
})
export class UsuariosComponent implements OnInit {
  subscription = this._stompService.subscribe('/topic/usuarios');
  todosUsuarios: any;
  usuarios: any = [];

  chart = new Chart({
    id : 'chart-1',
    data : {
      "backgroundColor":'#FBFCFE',
      "type": "radar",
      "legend":{

      },
      "plot": {
        "aspect": "area",
        "background-color":'#FBFCFE',
        "active-area":true
      },
      "plotarea":{
        "margin":'dynamic'
      },
      "scale-v": {
        "values": "0:1:0.05",
        "labels": ["", "", "", "", "", ""],
        "ref-line": {
          "line-color": "none"
        },
        "guide": {
          "line-style": "solid",
          "line-color":'#D7D8D9'
        }
      },
      "scale-k": {
        "values": [16,17,18,19,20,21,22,23,24,25,26,27,28,29,30],
        "labels" : [16,17,18,19,20,21,22,23,24,25,26,27,28,29,30],
        "format": "%vC°",
        "aspect": "circle", //To set the chart shape to circular.
        "guide": {
          "line-style": "solid",
          "line-color" : "#1E5D9E",
        },
        "item": {
          "padding": 5,
          "font-color" : "#1E5D9E",
          "font-family": 'Montserrat'
        },
      },
      "series": []
    },
    height : 700,
    width : 600
  });

  constructor(
    private _stompService: StompService,
    private _http: Http,
  private _recomendacaOService : RecomendacaoService) { }

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
      new Usuario(5, this._http),
      new Usuario(6, this._http),
      new Usuario(7, this._http),
      new Usuario(8, this._http)
    ];

    this.subscription.map((message: Message) => {
      return JSON.parse(message.body);
    }).subscribe((msg_body: any) => {
      this.usuarios = msg_body
    });


    this._recomendacaOService.change.subscribe((recomendacao: any)=>{
      recomendacao.userTemperatureProfiles.forEach(userProfile=>{
        userProfile.v = userProfile.votes
          .reduce(function(anterior, atual){return anterior.concat(atual.rating)},[]);

        let maxValue = this.arrayMax(userProfile.v);
        let minValue = this.arrayMin(userProfile.v);
        userProfile.v = userProfile.v.map(this.normalize(minValue, maxValue))
      });

      //this.chart.data["series"]=[];

      console.log(this.chart.data["series"]);

      let background = ["#800080", "#000080", "#008080", "#008000", "#808000", "#323e4a", "#00FFFF", "#0000FF", "#FF00FF"];
      let lineColors = ["#323e4a", "#323e4a","#323e4a","#323e4a","#323e4a","#323e4a","#323e4a","#323e4a"];

      let series = recomendacao.userTemperatureProfiles.reduce(function(anterior, atual, index){
        return anterior.concat({
          "background-color": background[index],
          "line-color": background[index],
          "values": atual.v,
          "text": `Usuario_${atual.idUsuario}`
        })
      },[]);

      this.chart.data["series"] = series;
      console.log(series);

      console.log(recomendacao)
    })
  }

  arrayMax(array) {
    return array.reduce((a, b) => Math.max(a, b));
  }

  arrayMin(array) {
    return array.reduce((a, b) => Math.min(a, b));
  }

  normalize(min, max) {
  let delta = max - min;
  return function (val) {
    if (delta == 0) {
      return 0;
    }
    return (val - min) / delta;
  };
}

  get getUsuariosMarcados() {
    return this.todosUsuarios
      .filter(opt => opt.checked)
      .map(opt => opt.value)
  }

}
