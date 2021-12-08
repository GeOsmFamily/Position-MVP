import { Data } from './../../interfaces/userInterface';
import { Commercial, Etablissement } from './../../interfaces/etablissementInterface';
import { ComponentHelper } from './../../helpers/componentHelper';
import { Component, OnInit, ViewChild } from '@angular/core';
import { TileLayer, View, XYZ, Map, Zoom, Feature } from 'src/app/modules/ol';
import {defaults} from 'ol/control';
import Geolocation from 'ol/Geolocation';
import * as proj4 from 'proj4';


import {
  Fill,
  Icon,
  Stroke,
  Style,
  VectorLayer,
  VectorSource,
  Text,
} from 'src/app/modules/ol';
import Point from 'ol/geom/Point';

import { MapHelper } from 'src/app/helpers/mapHelper';
import { FicheEntrepriseComponent } from './fiche-entreprise/fiche-entreprise.component';
import { LoginComponent } from '../auth/login/login.component';
import { EtablissementComponent } from './etablissement/etablissement.component';
import { PositionApiService } from 'src/app/services/position-api/position-api.service';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';




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


coordinates=[0,0]
etablissements:any | undefined
horaires=new Array()
telephones=new Array()
numero_whatsapp=new Array()
url_position=environment.url_image
  imagesCourousel=new Array()


@ViewChild(FicheEntrepriseComponent, { static: true })
ficheEntrepriseComponent: FicheEntrepriseComponent | undefined;

@ViewChild(EtablissementComponent, { static: true })
etablissementComponent: EtablissementComponent | undefined;

@ViewChild(LoginComponent, { static: true })
loginComponent: LoginComponent | undefined;




  constructor( private activatedRoute: ActivatedRoute,public positionApi:PositionApiService, public componentHelper: ComponentHelper) {

  }

  ngOnInit(): void {

this.initialiazeMap()
this.handleMapParamsUrl()
this.getPosition()

//this.userLocation()

  }


  handleMapParamsUrl() {
    var mapHelper = new MapHelper();

    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['etablissements']) {

        var layers = params['etablissements'].split(',');
       this.positionApi.getEtablissement(layers[0])


     

      }
    });
  }

  ngAfterViewInit(): void {


    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
   /* map.on('pointermove', function(event) {



    });*/
    this.mapClicked()
    this.componentHelper.setComponent('FicheEntrepriseComponent',this.ficheEntrepriseComponent)
    this.componentHelper.setComponent('EtablissementComponent',this.etablissementComponent)





  }

  //return map
  getMap(): Map {
    return map;
  }

  //map initialization
 initialiazeMap(){
  map.setTarget('map');
}



//get user location
getPosition(){
  let cartoHelpeClass = new MapHelper()

  if (cartoHelpeClass.getLayerByName('user_position').length == 0) {
    cartoHelpeClass.geolocateUser()
  } else {
    let featurePosition = cartoHelpeClass.getLayerByName('user_position')[0].getSource().getFeatures()[0]
    cartoHelpeClass.fit_view(featurePosition.getGeometry(), 19)
  }
}

/*
userLocation(){
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
geolocation.on('change:position', function () {
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
*/


/**
   * Event if mapClicked
   */
 mapClicked() {

  let mapHelper = new MapHelper();
  map.on('singleclick', (event) => {
     var pixel=map?.getEventPixel(event.originalEvent)
     var feature=map?.forEachFeatureAtPixel(
       pixel,function(feature,layer){
         return feature
       }
     )




      if(feature?.get('type')=="position"){

          this.componentHelper.openFicheEntreprise(feature)
      }

});
}



}

