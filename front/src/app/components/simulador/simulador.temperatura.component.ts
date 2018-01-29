import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import 'rxjs/add/operator/map';
import {Http, Headers} from '@angular/http';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {AirService} from "../air.service";

@Component({
  selector: 'simulador',
  templateUrl: 'simulador.temperatura.component.html',
  styleUrls: ['simulador.temperatura.component.css']
})
export class SimuladorTemperaturaComponent implements OnInit {
  @ViewChild(ModalDirective) modal: ModalDirective;

  temperatureExterna: boolean = false;
  temperatureInterna: boolean = false;
  temperatureInternaAr: boolean = false;

  constructor(
    private _http: Http, private _airService: AirService) { }

  setPreference(usuario) {
    // let headers = new Headers({
    //   'Content-Type': 'application/json'
    // });
    // let temperatura = prompt("Insira a preferência do usuário (16º-30º)");
    // if(temperatura) {
    //   this._http.post(`http://localhost:8733/${usuario}/vote`,
    //     temperatura,
    //     {headers: headers})
    //     .subscribe(data => {
    //     }, error => {
    //       console.log(JSON.stringify(error.json()));
    //     });
    // }
  }

  private timerExterna: any;
  private timerInterna: any;
  externa: any = 16;
  interna: any = 16;

  toogleTemperaturaExterna(){
    this.temperatureExterna = !this.temperatureExterna;

    if(this.temperatureExterna){
      this.timerExterna = setInterval(()=>{
        this.sendTemperature('externalTemperature', this.externa);
      }, 5000);
    } else {
      clearInterval(this.timerExterna);
    }
  }

  toogleTemperaturaInterna(){
    this.temperatureInterna = !this.temperatureInterna;

    if(this.temperatureInterna){
      this.timerInterna = setInterval(()=>{
        this.sendTemperature('internalTemperature', this.interna);
      }, 5000);
    } else {
      clearInterval(this.timerInterna);
    }
  }

  sendTemperature(sensor, valor) {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
      this._http.post(`http://localhost:8732/${sensor}`,
        valor,
        {headers: headers})
        .subscribe(data => {
        }, error => {
          console.log(JSON.stringify(error.json()));
        });

  }

  ngOnInit(): void {
    this._airService.change.subscribe(temperature => {
      if(this.temperatureInternaAr) {
        this.interna = temperature;
      }
    });

  }
}
