import {Injectable, Output} from "@angular/core";
import {EventEmitter} from "selenium-webdriver";
import {Observer, Subject} from "rxjs";

@Injectable()
export class AirService {

  temperatura:string = "0";

  change: Subject<any> = new Subject();

  setTemperature(temperatura) {
    this.temperatura = temperatura;
    this.change.next(this.temperatura);
  }

}
