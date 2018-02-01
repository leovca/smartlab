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

  ngOnInit() {
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


  this._recomendacaOService.change.subscribe((recomendacao: any) => {
    recomendacao.userTemperatureProfiles.forEach(userProfile => {
      userProfile.v = userProfile.votes
        .reduce(function (anterior, atual) {
          return anterior.concat(atual.rating)
        }, []);

      let maxValue = this.arrayMax(userProfile.v);
      let minValue = this.arrayMin(userProfile.v);
      userProfile.v = userProfile.v.map(this.normalize(minValue, maxValue))
    });

    let series2 = recomendacao.userTemperatureProfiles.reduce(function (anterior, atual, index) {
      anterior[atual.idUsuario] = {
        id: 1,
        data: atual.v,
        label: `Usuario_${atual.idUsuario}`
      };
      return anterior;
    }, {});

    let series2Ajustas = [];
    for (let i = 1; i <= 8; i++) {
      if(series2[i]){
        series2Ajustas= series2Ajustas.concat(series2[i])
      } else {
        series2Ajustas = series2Ajustas.concat({data:[], label: `Usuário_${i}`})
      }
    }

    this.radarChartData = series2Ajustas;

    let valores = recomendacao.recomendacaoList.map((rec) => rec.consenso);
    let algNames = recomendacao.recomendacaoList.map((rec) => rec.nameAlgorithms);

    this.barChartLabels = algNames;
    this.barChartData = [{
        data: valores,
        label: 'Valores'
      }];

    let temps = recomendacao.userTemperatureProfiles.map(u=> Math.round(u.temperatura));
    let recs = recomendacao.recomendacaoList.map(rec => Math.round(rec.consenso));

    this.barChartDataErro = recs.map(v=>temps.map(t=> Math.pow((t-v),2)).reduce((a, b) => a + b, 0)/temps.length);
  })
}

  public barChartLabels:string[] = ["AverageWithoutMisery", "LeastMisery", "MostPleasure", "Multiplicative", "BordaCount", "Knn"];
  public barChartType:string = 'bar';
  public barChartLegend:boolean = false;
  public barChartOptions:any = {
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };

  public barChartDataErro:any[] = [
    {data: [0,0,0,0,0,0], label: 'Valores'}
  ];

  public barChartData:any[] = [
    {data: [0,0,0,0,0,0], label: 'Valores'}
  ];

  public radarChartLabels:any[] = [16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];

  public radarChartData:any = [
    {data: [], label: 'Usuario 1'},
    {data: [], label: 'Usuario 2'},
    {data: [], label: 'Usuario 3'},
    {data: [], label: 'Usuario 4'},
    {data: [], label: 'Usuario 5'},
    {data: [], label: 'Usuario 6'},
    {data: [], label: 'Usuario 7'},
    {data: [], label: 'Usuario 8'}
  ];
  public radarChartType:string = 'radar';

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
