import { MapHelper } from 'src/app/helpers/mapHelper';
import { FilterOptionInterface } from 'src/app/interfaces/filterOptionInterface';
import {
  Cluster,
  Feature,
  GeoJSON,
  VectorLayer,
  VectorSource,
} from 'src/app/modules/ol';
import { environment } from 'src/environments/environment';

export class HandleSousCategoriesSearch {
  features: any | undefined;
  geojson: any;
  formatDataForTheList(responseData: any): Array<FilterOptionInterface> {
    var responses = Array();

    console.log(responseData);

    responseData.forEach((element: any) => {
      console.log(element.etablissements);
      element.etablissements.forEach((elementE: any) => {
        var geometry = {
          type: 'Point',
          coordinates: [
            elementE.batiment.longitude,
            elementE.batiment.latitude,
          ],
        };

        responses.push({
          type: 'Feature',
          geometry: geometry,
          properties: elementE,
        });
      });
    });

    this.geojson = {
      type: 'FeatureCollection',
      features: responses,
    };

    console.log(this.geojson);

    var response: Array<FilterOptionInterface> = [];
    for (let index = 0; index < responseData.length; index++) {
      const element = responseData[index];

      if (responseData.length > 0) {
        var details = Array();
        if (this._formatType(element)) {
          details.push(this._formatType(element));
        }

        response.push({
          name: responseData[index].nom,
          id: responseData[index].id,
          details: details.join(', '),
          logo: responseData[index].logo_url,
          geojson: this.geojson,
          typeOption: 'souscategories',
        });
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

  _formatType(option: any) {
    return option.nomCategorie;
  }

  optionSelected(emprise: FilterOptionInterface) {
    let mapHelper = new MapHelper();

    if (mapHelper.getLayerByName('searchResultLayer').length > 0) {
      var searchResultLayer = new VectorLayer();
      searchResultLayer = mapHelper.getLayerByName('searchResultLayer')[0];

      const vectorSource = new VectorSource({
        features: new GeoJSON().readFeatures(emprise.geojson),
      });

      const clusterSource = new Cluster({
        distance: parseInt('50'),
        minDistance: parseInt('30'),
        source: vectorSource,
      });

      searchResultLayer.getSource().clear();
      searchResultLayer.setSource(clusterSource);
      console.log(emprise.geojson);
      //  searchResultLayer.getSource().addFeatures(vectorSource);
    }
  }

  /* _addGeometryAndZoomTO(emprise: FilterOptionInterface) {
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
  }*/
}
