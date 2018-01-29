import {Component, OnInit} from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import 'rxjs/add/operator/map';
import {AirService} from "../air.service";

@Component({
  selector: 'ambiente',
  templateUrl: 'ambiente.component.html',
  styleUrls: ['ambiente.component.css']
})
export class AmbienteComponent implements OnInit {


  subscription = this._stompService.subscribe('/topic/observer');

  public state: Observable<string>;

  constructor(private _stompService: StompService, private _airService: AirService) { }

  valor: any = {
    external: "--",
    internal: "--",
    airTemp: "--"
  };

  ngOnInit(): void {
    // this.state = this._stompService.state
    //   .map((state: number) => {
    //     StompState[state]
    //     console.log(state)
    //   });
    //
    //
    console.log('Status init');
    this.subscription.map((message: Message) => {
      return JSON.parse(message.body);
    }).subscribe((msg_body: any) => {
      this.valor = msg_body;
      if(this.valor.airTemp){
        this._airService.setTemperature(this.valor.airTemp);
      } else {
        this._airService.setTemperature(this.valor.external);
      }
    });
  }

}
