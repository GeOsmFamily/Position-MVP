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
    console.log("hello")
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['etablissements']) {

        var layers = params['etablissements'].split(',');
       var  etablissement=this.positionApi.getEtablissement(layers[0])
        console.log(layers + " nnnnnnnnn")
        console.log(etablissement!)

        //creation d'un vectorlayer
        var searchResultLayer = new VectorLayer();
        var cover=environment.url_image+etablissement
      /*  var image=environment.url_image+this.etablissements[0].images[0].imageUrl
        console.log(cover+ "" +image)
          this.imagesCourousel.push(cover)
        this.imagesCourousel.push(image)
        var feature = new Feature();
        var textLabel = this.etablissements[0].name;
        var description=this.etablissements[0].description
        console.log("description"+description)
        feature.set('imagesCourousel',this.imagesCourousel)
        feature.set('textLabel', textLabel);
        feature.set('id',this.etablissements[0].id);
        feature.set('logo_url', environment.url_image + this.etablissements[0].sous_categories[0].categorie.logoUrl);
        feature.set('description',this.etablissements[0].description);
        feature.set('type',"position");
        feature.set('nomCategorie',this.etablissements[0].nomCategorie);
        feature.set('nomSousCategorie',this.etablissements[0].sous_categories[0].nom)
        feature.set('cover',this.etablissements[0].cover);
        feature.set('siteInternet',this.etablissements[0].siteInternet);
        feature.set('indication',this.etablissements[0].batiment.indication);

        feature.setGeometry(this.etablissements[0].geometry);
  var i=0
        for (let index = 0; index < this.etablissements[0].horaires.length; index++){
          if(this.etablissements[0].horaires[index].jour == "Lundi" && i==0){
            i++
            this.horaires?.push({"tous_les_jours":this.etablissements[0].horaires[index].jour,"heureOuverture":this.etablissements[0].horaires[index].heureOuverture,"heureFermeture":this.etablissements[0].horaires[index].heureFermeture})

          }
          if(this.etablissements[0].horaires[index].jour == "Samedi"){
            this.horaires?.push({"Samedi":this.etablissements[0].horaires[index].jour,"heureOuverture":this.etablissements[0].horaires[index].heureOuverture,"heureFermeture":this.etablissements[0].horaires[index].heureFermeture})

          }
          if(this.etablissements[0].horaires[index].jour == "Dimanche"){
            this.horaires?.push({"Dimanche":this.etablissements[0].horaires[index].jour,"heureOuverture":this.etablissements[0].horaires[index].heureOuverture,"heureFermeture":this.etablissements[0].horaires[index].heureFermeture})

          }
             // console.log(categories[index].nom)
        }
        feature.set('horaires',this.horaires)

        var numero_whatsapp=new Array()
        for (let index = 0; index < this.etablissements[0].telephones.length; index++){

          if(this.etablissements[0].telephones[index].principal==true){
           // this.telephones?.push({"principal":emprise.telephones[index].numero})
            feature.set("telephonePrincipal",this.etablissements[0].telephones[index].numero);
          }
          else{
            this.telephones.push(this.etablissements[0].telephones[index].numero)
          }


        }
       // this.telephones?.push({"whatsapp":this.numero_whatsapp})
        feature.set('telephones',this.telephones)
        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = this.etablissements[0].geometry.getExtent();

        mapHelper.fit_view(extent, 16);*/

       // this.shareService.addLayersFromUrl(layers);
      }
    });
  }

  ngAfterViewInit(): void {


    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
   /* map.on('pointermove', function(event) {

      console.log( event.coordinate)

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



      console.log("feature1")
      console.log(feature)
      if(feature?.get('type')=="position"){
        console.log("position")
          this.componentHelper.openFicheEntreprise(feature)
      }





   // console.log( this.componentHelper.openEtablissement())
  console.log("map has been clicked")
});
}



}

