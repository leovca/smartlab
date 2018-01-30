import {Injectable, Output} from "@angular/core";
import {EventEmitter} from "selenium-webdriver";
import {Observer, Subject} from "rxjs";

@Injectable()
export class RecomendacaoService {

  recomendacao: any = {};

  change: Subject<any> = new Subject();

  setRecomencadao(recomendacao) {
    this.recomendacao = recomendacao;
    this.change.next(this.recomendacao);
  }

}
