export class Chart {
  id: String;
  data: Object;
  height: any;
  width: any;
  constructor(config: Object) {
    this.id = config['id'];
    this.data = config['data'];
    this.height = config['height'] || 300;
    this.width = config['width'] || 600;
  }
}
