import { environment } from 'src/environments/environment';
import { Feature, GeoJSON, VectorLayer } from 'src/app/modules/ol';

import { FilterOptionInterface } from 'src/app/interfaces/filterOptionInterface';
import { MapHelper } from 'src/app/helpers/mapHelper';

export class HandleEtablissementsSearch {
  horaires = new Array();
  telephones = new Array();
  numero_whatsapp = new Array();
  chaine_num_whatsapp=""
  url_position = environment.url_image;
  imagesCourousel = new Array();

  formatDataForTheList(responseData: any): Array<FilterOptionInterface> {
    var responses = Array();

    responseData.forEach((element: any) => {
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
    for (let index = 0; index < geojson.features.length; index++) {
      const element = geojson.features[index];
      var features = new GeoJSON().readFeatures(element, {
        dataProjection: 'EPSG:4326',
        featureProjection: 'EPSG:3857',
      });

      if (features.length > 0) {
        var details = Array();
        if (this._formatType(element)) {
          details.push(this._formatType(element));
        }

       
        response.push({
          name: features[0].get('nom'),
          id: features[0].get('id'),
          adresse: features[0].get('description'),
          geometry: features[0].getGeometry(),
          details: details.join(', '),
          logo: environment.url_image+features[0].get('logo_url'),
          typeOption: 'etablissements',
          ...features[0].getProperties(),
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
    return (
      option.properties.nomCategorie + ',' + option.properties.nomSousCategorie
    );
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
        var cover = environment.url_image + emprise.cover;
        var image = environment.url_image + emprise.images[0].imageUrl;

        this.imagesCourousel.push(cover);
        this.imagesCourousel.push(image);
        var feature = new Feature();
        var textLabel = emprise.name;
        var description = emprise.description;

        feature.set('imagesCarousel', this.imagesCourousel);
        feature.set('textLabel', textLabel);
        feature.set('id', emprise.id);
        feature.set(
          'logo_url',
          environment.url_image + emprise.sous_categories[0].categorie.logoUrl
        );
       
        feature.set('description', emprise.description);
        feature.set('type', 'position');
        feature.set('adresse',emprise.batiment.rue+", "+emprise.batiment.ville+", bâtiment "+emprise.batiment.nom+", étage "+emprise.etage)
       
        feature.set('nomCategorie', emprise.nomCategorie);
        feature.set('nomSousCategorie', emprise.sous_categories[0].nom);
        feature.set('cover', emprise.cover);
        feature.set('siteInternet', emprise.siteInternet);
        feature.set('indication', emprise.indicationAdresse);
        feature.set('codePostal',emprise.codePostal)
        feature.set('etage',emprise.etage)
        feature.set('motCle',emprise.autres)
        feature.setGeometry(emprise.geometry);
        var i = 0;
        for (let index = 0; index < emprise.horaires.length; index++) {
          if (emprise.horaires[index].jour == 'Lundi' && i == 0) {
            i++;
            this.horaires?.push({
              tous_les_jours: emprise.horaires[index].jour,
              heureOuverture: emprise.horaires[index].heureOuverture,
              heureFermeture: emprise.horaires[index].heureFermeture,
            });
          }
          if (emprise.horaires[index].jour == 'Samedi') {
            this.horaires?.push({
              Samedi: emprise.horaires[index].jour,
              heureOuverture: emprise.horaires[index].heureOuverture,
              heureFermeture: emprise.horaires[index].heureFermeture,
            });
          }
          if (emprise.horaires[index].jour == 'Dimanche') {
            this.horaires?.push({
              Dimanche: emprise.horaires[index].jour,
              heureOuverture: emprise.horaires[index].heureOuverture,
              heureFermeture: emprise.horaires[index].heureFermeture,
            });
          }

        }
        feature.set('horaires', this.horaires);

       
        for (let index = 0; index < emprise.telephones.length; index++) {
          if (emprise.telephones[index].principal == 1) {
            // this.telephones?.push({"principal":emprise.telephones[index].numero})
            feature.set('telephonePrincipal', emprise.telephones[index].numero);
           // console.log(emprise.telephones[index].numero)
          } else {
            this.numero_whatsapp.push(emprise.telephones[index].numero);
            //console.log(emprise.telephones[index].numero)
            

          }
        }
        // this.telephones?.push({"whatsapp":this.numero_whatsapp})
        feature.set('telephones', this.telephones);
        feature.set('whatsapp',this.numero_whatsapp)

        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = emprise.geometry.getExtent();

        mapHelper.fit_view(extent, 16);
      }
    }
  }
}
