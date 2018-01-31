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

  chartTemps = new Chart({
    id : 'chart-2',
    height : 700,
    width : 900,
  data : {
  "globals": {
    "font-family": "Helvetica"
  },
  "type": "bar",
  "background-color": "white",
  "title": {
    "color": "#606060",
    "background-color": "white",
    "text": "Temperaturas por Algortimo"
  },
  "scale-y": {
    "line-color": "none",
    "tick": {
      "line-color": "none"
    },
    "guide": {
      "line-style": "solid"
    },
    "item": {
      "color": "#606060"
    }
  },
  "scale-x": {
    "values": [],
    "line-color": "#C0D0E0",
    "line-width": 1,
    "tick": {
      "line-width": 1,
      "line-color": "#C0D0E0"
    },
    "guide": {
      "visible": false
    },
    "item": {
      "color": "#606060"
    }
  },
  "crosshair-x": {
    "marker": {
      "visible": false
    },
    "line-color": "none",
    "line-width": "0px",
    "scale-label": {
      "visible": false
    },
    "plot-label": {
      "text": "%data-browser: %v% of total",
      "multiple": true,
      "font-size": "12px",
      "color": "#606060",
      "background-color": "white",
      "border-width": 3,
      "alpha": 0.9,
      "callout": true,
      "callout-position": "bottom",
      "shadow": 0,
      "placement": "node-top",
      "border-radius": 4,
      "offsetY":-20,
      "padding":8,
      "rules": [
        {
          "rule": "%i==0",
          "border-color": "#1976d2"
        },
        {
          "rule": "%i==1",
          "border-color": "#424242"
        },
        {
          "rule": "%i==2",
          "border-color": "#388e3c"
        },
        {
          "rule": "%i==3",
          "border-color": "#ffa000"
        },
        {
          "rule": "%i==4",
          "border-color": "#7b1fa2"
        },
        {
          "rule": "%i==5",
          "border-color": "#c2185b"
        }
      ]
    }
  },
  "plot": {
    "data-browser": [
      "<span style='font-weight:bold;color:#1976d2;'>1Internet Explorer</span>",
      "<span style='font-weight:bold;color:#424242;'>Chrome</span>",
      "<span style='font-weight:bold;color:#388e3c;'>Firefox</span>",
      "<span style='font-weight:bold;color:#ffa000;'>Safari</span>",
      "<span style='font-weight:bold;color:#7b1fa2;'>Opera</span>",
      "<span style='font-weight:bold;color:#c2185b;'>Unknown</span>"
    ],
    "cursor": "hand",
    "value-box": {
      "text": "%v%",
      "text-decoration": "underline",
      "color": "#606060"
    },
    "tooltip": {
      "visible": false
    },
    "rules": [
      {
        "rule": "%i==0",
        "background-color": "#1976d2"
      },
      {
        "rule": "%i==1",
        "background-color": "#424242"
      },
      {
        "rule": "%i==2",
        "background-color": "#388e3c"
      },
      {
        "rule": "%i==3",
        "background-color": "#ffa000"
      },
      {
        "rule": "%i==4",
        "background-color": "#7b1fa2"
      },
      {
        "rule": "%i==5",
        "background-color": "#c2185b"
      }
    ]
  },
  "series": [
    {
      "values": []
    }
  ]
}});

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

    let background = ["#800080", "#000080", "#008080", "#008000", "#808000", "#323e4a", "#00FFFF", "#0000FF", "#FF00FF"];

    let series = recomendacao.userTemperatureProfiles.reduce(function (anterior, atual, index) {
      return anterior.concat({
        "background-color": background[index],
        "line-color": background[index],
        "values": atual.v,
        "text": `Usuario_${atual.idUsuario}`
      })
    }, []);

    let series2 = recomendacao.userTemperatureProfiles.reduce(function (anterior, atual, index) {
      anterior[atual.idUsuario] = {
        id: 1,
        data: atual.v,
        label: `Usuario_${atual.idUsuario}`
      };
      return anterior;
    }, {});

    this.chart.data["series"] = series;

    let series2Ajustas = [];
    for (let i = 1; i <= 8; i++) {
      if(series2[i]){
        series2Ajustas= series2Ajustas.concat(series2[i])
      } else {
        series2Ajustas = series2Ajustas.concat({data:[], label: `Usuarrio_${i}`})
      }
    }

    this.radarChartData = series2Ajustas;
  })
}

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
