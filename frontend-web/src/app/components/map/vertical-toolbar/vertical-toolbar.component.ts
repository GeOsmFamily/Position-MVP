import { Component, OnInit , Input} from '@angular/core';
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

  constructor(public mapcomponent:MapComponent) { }

  ngOnInit(): void {
  }

  userLocation(){
    // Begin geolocation
    this.mapcomponent.userLocation()
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
