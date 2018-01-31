import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import 'rxjs/add/operator/map';
import {Http, Headers} from '@angular/http';
import {AirService} from "../air.service";

@Component({
  selector: 'configuracoes',
  templateUrl: 'configuracoes.component.html',
  styleUrls: ['configuracoes.component.css']
})
export class ConfiguracoesComponent implements OnInit {

  constructor(
    private _http: Http) { }

  algorimo: any;

  ngOnInit(): void {

  }

  salvarConfiguracao() {

    this._http.post("http://localhost:5153/setConfig",
      {
        "algorithmsType": this.algorimo,
        "algortimoPreference": 1
      })
      .subscribe(data => {}, error => {
        console.log(JSON.stringify(error.json()));
      });
  }

}
