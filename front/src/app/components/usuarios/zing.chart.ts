declare var zingchart: any;

import {Component, AfterViewInit, OnDestroy, NgZone, OnInit} from "@angular/core";
import {Chart} from "./chart";
@Component({
  selector : 'zingchart',
  inputs : ['chart'],
  template : `  
   <div id='{{chart.id}}'></div>  
  `
})
export class ZingChart implements AfterViewInit, OnDestroy, OnInit {

  chart : Chart;

  constructor(private zone:NgZone) {
  }

  ngAfterViewInit() {
    this.renderChart();
  }

  renderChart (){
    this.zone.runOutsideAngular(() => {
      zingchart.render({
        id : this.chart['id'],
        data : this.chart['data'],
        width : this.chart['width'],
        height: this.chart['height']
      });
    });
  }

  ngOnInit(): void {
    setInterval(()=>{
        this.renderChart();
    }, 5000);
  }

  ngOnDestroy() {
    zingchart.exec(this.chart['id'], 'destroy');
  }
}
