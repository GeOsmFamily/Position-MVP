import { Injectable } from '@angular/core';
import {
  ImageLayer,
  Map,
  GeoJSON,
  VectorSource,
  Style,
  Fill,
  RasterSource,
  boundingExtent,
  VectorLayer,
  ScaleLine,
  MousePosition,
  createStringXY,
  TileLayer,
  XYZ,
  TileWMS,
  ImageWMS,
  LayerGroup,
  Cluster,
  CircleStyle,
  Stroke,
  Icon,
  transformExtent,
  Text,
  Point,
  Feature,
  Geolocation
} from '../modules/ol';
import { environment } from '../../environments/environment';

import { map as geoportailMap } from './../components/map/map.component';


import { HttpErrorResponse } from '@angular/common/http';
import { timer } from 'rxjs/internal/observable/timer';
import { Translate } from 'ol/interaction';
import Collection from 'ol/Collection';

const typeLayer = [
  'geosmCatalogue',
  'draw',
  'mesure',
  'mapillary',
  'exportData',
  'comments',
  'other',
  'routing',
];

@Injectable()
export class MapHelper {
  map: Map | undefined;
  environment = environment;

  constructor(map?: Map) {
    if (map) {
      this.map = map;
      console.log("hello map")
    } else {
      this.map = geoportailMap;
    }
  }

  /**
   * initialise layer of the geolocation layer and add it to the map
   */
   initialiserLayerGeoLocalisation() {

    let geolocalisationLayer = new VectorLayer({
                // @ts-ignore

      nom: "user_position",
      source: new VectorSource({
      }),

    });
    geolocalisationLayer.setZIndex(99999999)
    this.map?.addLayer(geolocalisationLayer)
  }


/**
   * Get list of layer by their names
   * @param name string 'nom' of layer to search
   * @param isLayerGroup boolean is the layeys we want are in a layergroup ?
   * @return Array<any>
   */
 getLayerByName(name: string, isLayerGroup: boolean = false): Array<any> {
  var layer_to_remove = []

  if (isLayerGroup) {
    var all_layers = this.map?.getLayers().getArray()
  } else {
    var all_layers = this.map?.getLayerGroup().getLayers().getArray()
  }

  for (let index = 0; index < all_layers!.length; index++) {
    var layer = all_layers![index]
    if (layer.get('nom') == name) {
      layer_to_remove.push(layer)
      console.log(layer.get('nom'))
    }

  }
  return layer_to_remove
}


  geolocateUser() {
    if (this.getLayerByName('user_position').length == 0) {
      this.initialiserLayerGeoLocalisation()
    }
    let geolocalisationLayer = this.getLayerByName('user_position')[0]
    geolocalisationLayer.getSource().clear()

    let positionFeature = new Feature();
    positionFeature.setStyle(
      [

      new Style({
        image: new Icon({
        scale: 0.1,
          src: '/assets/location-marker.svg',
          anchor:[0.5,0.95]
        }),
        // text: new Text({
        //   font: "15px Calibri,sans-serif",
        //   fill: new Fill({ color: "#000" }),
        //   text:"Your position",
        //   stroke: new Stroke({ color: "#000", width: 1 }),
        //   padding: [10, 10, 10, 10],
        //   backgroundFill:new Fill({ color: "#fff" }),
        //   offsetX: 0,
        //   offsetY: 30,
        // })
      })
    ]
    );

    let geolocation = new Geolocation({
      // enableHighAccuracy must be set to true to have the heading value.
      trackingOptions: {
        enableHighAccuracy: true,
      },
      tracking: true,
      projection: this.map?.getView().getProjection(),
    });

/*
    var translate1=this.markerInteractionFeature(positionFeature)
    this.map?.addInteraction(translate1);
    */

    geolocation.once('change:position', () => {
      var coordinates = geolocation.getPosition();
      positionFeature.setGeometry(coordinates ? new Point(coordinates) : undefined);
      if (coordinates) {

        this.fit_view(new Point(coordinates), 18)
      }
    });

     /* this.map?.on('pointermove', (e) => {
      if (e.dragging) return;
      var hit = this.map?.hasFeatureAtPixel(this.map?.getEventPixel(e.originalEvent));
      //this.map?.getTargetElement().style.cursor = hit ? 'pointer' : '';
    });*/
/*
    geolocation.on('change:position', () => {
      var coordinates = geolocation.getPosition();
      positionFeature.setGeometry(coordinates ? new Point(coordinates) :undefined);
      if (coordinates) {
        this.fit_view(new Point(coordinates), 18)
      }
    });

    geolocation.on('change:position', () => {
      var coordinates = geolocation.getPosition();
      positionFeature.setGeometry(coordinates ? new Point(coordinates) : undefined);
    });
*/
    geolocation.on('error', (e) => {
      console.error(e)
    });

    geolocalisationLayer.getSource().addFeature(positionFeature)
    console.log(geolocalisationLayer)
  }


/**
   * Fit view to an geometry or extent
   * @param geom Geometry|Extent geometry or extexnt
   * @param zoom number
   * @param padding
   */
 fit_view(geom:Point, zoom:number) {
  // if (padding == undefined) {
  //   padding = this.get_padding_map()
  // }
  // this.map.getView().animate({zoom: zoom}, {center: geom.getCoordinates()});

  // console.log([this.map.getSize()[0]- $('.sidenav-left').width() , this.map.getSize()[1] ])
  this.map?.getView().fit(geom, {
    'maxZoom': zoom,
    'size': this.map?.getSize(),
    'padding': [0, 0, 0, 0],
    'duration': 500
  })




}
//Put interaction on marker feature
markerInteractionFeature(featureMarker:any):Translate{

  return new Translate({
    features: new Collection([featureMarker])
  });
}
//Put interaction on marker feature
trackMarkerFeaturePosition(translateMarker:any,marker:Point):Translate{

  return translateMarker.on('translatestart',  function (evt: { coordinate: any; }) {
    //coordMarker1 = marker1.getCoordinates();
  console.log(evt.coordinate)
  });
}

moveMarkerOnMap(){

  //et categorie icon and replace it on src oparameter
  let positionFeature = new Feature();
  positionFeature.setStyle(
    [

    new Style({
      image: new Icon({
      scale: 0.1,
        src: '/assets/location-marker.svg',
        anchor:[0.5,0.95]
      }),
      // text: new Text({
      //   font: "15px Calibri,sans-serif",
      //   fill: new Fill({ color: "#000" }),
      //   text:"Your position",
      //   stroke: new Stroke({ color: "#000", width: 1 }),
      //   padding: [10, 10, 10, 10],
      //   backgroundFill:new Fill({ color: "#fff" }),
      //   offsetX: 0,
      //   offsetY: 30,
      // })
    })
  ]
  );



  let geolocation = new Geolocation({
    // enableHighAccuracy must be set to true to have the heading value.
    trackingOptions: {
      enableHighAccuracy: true,
    },
    tracking: true,
    projection: this.map?.getView().getProjection(),
  });

/*
  var translate1=this.markerInteractionFeature(positionFeature)
  this.map?.addInteraction(translate1);
  */

  geolocation.once('change:position', () => {
    var coordinates = geolocation.getPosition();
    positionFeature.setGeometry(coordinates ? new Point(coordinates) : undefined);
    
  });

  var translate1=this.markerInteractionFeature(positionFeature)
  this.map?.addInteraction(translate1);
  this.trackMarkerFeaturePosition(translate1,<Point>positionFeature.getGeometry())

}

}

