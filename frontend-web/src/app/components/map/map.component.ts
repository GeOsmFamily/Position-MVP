import { Component, OnInit } from '@angular/core';
import { TileLayer, View, XYZ, Map, Zoom, Feature } from 'src/app/modules/ol';
import {defaults} from 'ol/control';
import Geolocation from 'ol/Geolocation';
import * as proj4 from 'proj4';
import Style from 'ol/style/Style';

import { Circle as CircleStyle, Fill, Stroke, Text, Icon } from 'ol/style.js';
import Point from 'ol/geom/Point';
import VectorLayer from 'ol/layer/Vector';
import VectorSource from 'ol/source/Vector';



export const map = new Map({
  layers: [
    new TileLayer({
      source: new XYZ({
        crossOrigin: 'anonymous',
        url: 'https://api.mapbox.com/styles/v1/mapbox/streets-v9/tiles/256/{z}/{x}/{y}?access_token=pk.eyJ1IjoibGFnaHJpc3NpIiwiYSI6ImNqMmxwOWFyZjAwMHYycXFrc3IydzNwanMifQ.SK90mbaIxLVKh4vSRxsHFA',
      }),
    }),
  ],

  view: new View({
    center: [0, 0],
    zoom: 6,
  }),

});

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
map:Map|undefined

coordinates=[0,0]

  constructor() { }

  ngOnInit(): void {
this.initialiazeMap()

 // Begin geolocation
 var geolocation = new Geolocation({
  projection: map.getView().getProjection(),
  tracking: true,
  trackingOptions: {
    enableHighAccuracy: true,
    maximumAge: 2000
  }
});
const positionFeature = new Feature();
positionFeature.setStyle(
  new Style({
    image: new CircleStyle({
      radius: 6,
      fill: new Fill({
        color: '#3399CC',
      }),
      stroke: new Stroke({
        color: '#fff',
        width: 2,
      }),
    }),
  })
);
geolocation.once('change:position', function () {
  const coordinates = geolocation.getPosition();
  console.log(coordinates)
  positionFeature.setGeometry(coordinates ? new Point(coordinates) : undefined);
  map.setView(new View({
    center:coordinates ,
    zoom: 15
  }));
  new VectorLayer({
    map: map,
    source: new VectorSource({
      features: [ positionFeature],
    }),
  });
});

  }

  ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    map.on('pointermove', function(event) {

      console.log( event.coordinate)

    });
  }

  getMap(): Map {
    return map;
  }
  //map initialization
 initialiazeMap(){
  map.setTarget('map');
}


}

