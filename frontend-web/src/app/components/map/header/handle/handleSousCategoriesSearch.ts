import { MapHelper } from "src/app/helpers/mapHelper";
import { FilterOptionInterface } from "src/app/interfaces/filterOptionInterface";
import { Feature, GeoJSON, VectorLayer } from "src/app/modules/ol";
import { environment } from "src/environments/environment";

export class HandleSousCategoriesSearch {

  formatDataForTheList(responseData: any): Array<FilterOptionInterface> {
    var responses = Array();




    responseData.etablissements.forEach((element:any) => {
      var geometry = {
        type: 'Point',
        coordinates: [element.batiment.longitude, element.batiment.latitude],
      };

      responses.push({
        type: 'Feature',
        geometry: geometry,
        properties: element,
      });
    });

     var geojson = {
      type: 'FeatureCollection',
      features: responses,
    };

    var response: Array<FilterOptionInterface> = [];
    for (let index = 0; index < responseData.length; index++) {
      const element = responseData[index];
      for (let index = 0; index < geojson.features.length; index++) {
        const elementGeojson = geojson.features[index];
        var features = new GeoJSON().readFeatures(element, {
          dataProjection: 'EPSG:4326',
          featureProjection: 'EPSG:3857',
        });



        if (responseData.length > 0) {
          var details = Array();
          if (this._formatType(element)) {
            details.push(this._formatType(element));
          }

          response.push({
            name: responseData[index].nom,
            id: responseData[index].id,
            details: details.join(', '),
            geometry: features[0].getGeometry(),
            logo: responseData[index].logo_url,
            typeOption: 'souscategories',
            ...features[0].getProperties(),
          });
        }
      }
    }

    return response;
  }

  displayWith(data: FilterOptionInterface): string {
    if (data) {
      return data.name + ' (' + data.details + ')';
    } else {
      return '';
    }
  }


  _formatType(option:any) {
    return option.nomCategorie;
  }

  optionSelected(emprise: FilterOptionInterface) {
    if (!emprise.geometry) {

    } else {
      this._addGeometryAndZoomTO(emprise);
    }
  }

  _addGeometryAndZoomTO(emprise: FilterOptionInterface) {
    if (emprise.geometry) {
      var mapHelper = new MapHelper();
      if (mapHelper.getLayerByName('searchResultLayer').length > 0) {
        var searchResultLayer = new VectorLayer();
        searchResultLayer = mapHelper.getLayerByName('searchResultLayer')[0];

        var feature = new Feature();
        var textLabel = emprise.name;

        feature.set('textLabel', textLabel);
        feature.set('logo_url', environment.url_image + emprise.logo_url);


        feature.setGeometry(emprise.geometry);

        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = emprise.geometry.getExtent();

        mapHelper.fit_view(extent, 16);
      }
    }
  }
}
