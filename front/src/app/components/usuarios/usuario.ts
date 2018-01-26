export class Usuario {
  private id: any;
  private checked: boolean;

  private timer: any;
  private _http: any;

  constructor(id, _http){
    this.id = id;
    this.checked = false;
    this._http = _http;
  }

  private enableTime(){
    this.timer = setInterval(()=>{
      this._http.post("http://localhost:8732/presence", {"idUsuario": this.id})
        .subscribe(data => {}, error => {
          console.log(JSON.stringify(error.json()));
        });
    }, 5000);
  }

  private disableTime(){
    clearInterval(this.timer);
  }

  public toogle(){
    this.checked = !this.checked;
    if(this.checked){
      this.enableTime();
    } else {
      this.disableTime();
    }
  }
  public getId(){
    return this.id;
  }

  public getCheckd(){
    return this.checked;
  }
}
