import { Component, OnInit , Input} from '@angular/core';
import { MapHelper } from 'src/app/helpers/mapHelper';
import {
  Feature,Map

} from 'src/app/modules/ol';
import { MapComponent } from '../map.component';
@Component({
  selector: 'app-vertical-toolbar',
  templateUrl: './vertical-toolbar.component.html',
  styleUrls: ['./vertical-toolbar.component.scss']
})
export class VerticalToolbarComponent implements OnInit {
  @Input() map: Map | undefined;
 maphelper= new MapHelper()
  constructor(public mapcomponent:MapComponent) { }

  ngOnInit(): void {
  }

  userLocation(){
    // Begin geolocation
    this.mapcomponent.getPosition()
  }


  //zoom function
  zoom(type: 'plus' | 'minus') {
    if (type == 'plus') {
      this.map?.getView().setZoom(this.map?.getView().getZoom()! + 1);
    } else {
      this.map?.getView().setZoom(this.map?.getView().getZoom()! - 1);
    }
  }
}
