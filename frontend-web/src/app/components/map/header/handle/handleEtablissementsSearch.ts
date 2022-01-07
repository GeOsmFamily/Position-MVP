import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Feature, GeoJSON, VectorLayer } from 'src/app/modules/ol';

import { FilterOptionInterface } from 'src/app/interfaces/filterOptionInterface';
import { MapHelper } from 'src/app/helpers/mapHelper';

export class HandleEtablissementsSearch {
  horaires = new Array();
  telephones = new Array();
  numero_whatsapp = new Array();
  chaine_num_whatsapp = '';
  url_position = environment.url_image;
  imagesCourousel = new Array();


   cover = ""
  image = ""
  imageBatiment = ""
  textLabel = ""
   description =""

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

        var logourl=""
        try {
          if(features[0].get('logo_url'))
          logourl= environment.url_image + features[0].get('logo_url')
        } catch (error) {

        }
        response.push({
          name: features[0].get('nom'),
          id: features[0].get('id'),
          adresse: features[0].get('description'),
          geometry: features[0].getGeometry(),
          details: details.join(', '),
          logo:logourl,
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
    try {

      return (
        option.properties.nomCategorie +
        ',' +
        option.properties.sous_categories[0].nom
      );
    } catch (error) {
      ///console.log("fake point")
      return

    }

  }

  optionSelected(emprise: FilterOptionInterface) {
    if (!emprise.geometry) {
    } else {
      this._addGeometryAndZoomTO(emprise);
    }
  }

  includehoraire(hs: any, index: number, jour: string) {
    this.horaires?.push({
      jour: hs[index].jour,
      heureOuverture: hs[index].heureOuverture,
      heureFermeture: hs[index].heureFermeture,
    });
  }
  _addGeometryAndZoomTO(emprise: FilterOptionInterface) {
    if (emprise.geometry) {

      var mapHelper = new MapHelper();
      if (mapHelper.getLayerByName('searchResultLayer').length > 0) {
        var searchResultLayer = new VectorLayer();
        searchResultLayer = mapHelper.getLayerByName('searchResultLayer')[0];



        try {
          this.cover = environment.url_image + emprise.cover;
          this.image = environment.url_image + emprise.images[0].imageUrl;

          this.textLabel = emprise.name;
          this.description = emprise.description;

        } catch (error) {
         // console.log("faux point")

        }
        var imageBatiment = environment.url_image + emprise.batiment.image;
        this.imagesCourousel.push(imageBatiment);
        this.imagesCourousel.push(this.cover);
        this.imagesCourousel.push(this.image);

        var feature = new Feature();


        feature.set('imagesCarousel', this.imagesCourousel);
        feature.set('textLabel', this.textLabel);
        feature.set('id', emprise.id);
        feature.set(
          'logo_url',
          environment.url_image + emprise.sous_categories[0].categorie.logoUrl
        );

        feature.set('description', emprise.description);
        feature.set('type', 'position');
        feature.set(
          'adresse',
          emprise.batiment.ville +
            ', ' +
            emprise.batiment.quartier +
            ', ' +
            emprise.batiment.rue
        );

        feature.set(
          'nomCategorieSousCategorie',
          emprise.sous_categories[0].nom + ', ' + emprise.nomCategorie
        );
        feature.set('nomSousCategorie', emprise.sous_categories[0].nom);

        feature.set('cover', emprise.cover);
        feature.set('siteInternet', emprise.siteInternet);
        feature.set('indication', emprise.indicationAdresse);
        feature.set('codePostal', emprise.codePostal);
        feature.set('etage', emprise.etage);
        feature.set('motCle', emprise.autres);
        feature.setGeometry(emprise.geometry);
        var i = 0;



        const sorter = {
          // "sunday": 0, // << if sunday is first day of week
          lundi: 1,
          mardi: 2,
          mercredi: 3,
          jeudi: 4,
          vendredi: 5,
          samedi: 6,
          dimanche: 7,
        };

        //@ts-ignore
        emprise.horaires.sort(function sortByDay(a, b) {
          let day1 = a.jour.toLowerCase();
          let day2 = b.jour.toLowerCase();
          //@ts-ignore
          return sorter[day1] - sorter[day2];
        });

       // console.log(emprise.horaires);
        var chaine_w=""
       for (let index = 0; index < emprise.horaires.length; index++) {
          emprise.horaires[index].heureOuverture=emprise.horaires[index].heureOuverture.slice(0,5)
          emprise.horaires[index].heureFermeture=emprise.horaires[index].heureFermeture.slice(0,5)

          }
        feature.set('horaires', emprise.horaires);

        for (let index = 0; index < emprise.telephones.length; index++) {
          if (emprise.telephones[index].principal == 1) {
            // this.telephones?.push({"principal":emprise.telephones[index].numero})
            feature.set('telephonePrincipal', emprise.telephones[index].numero);
            // console.log(emprise.telephones[index].numero)
          } else {
            this.numero_whatsapp.push(emprise.telephones[index].numero);
            chaine_w=chaine_w+emprise.telephones[index].numero+", "
            //console.log(emprise.telephones[index].numero)
          }
        }
        //console.log(chaine_w.slice(0,-2))
        // this.telephones?.push({"whatsapp":this.numero_whatsapp})
        feature.set('telephones', this.telephones);
        feature.set('whatsapp', chaine_w.slice(0,-2));

        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = emprise.geometry.getExtent();

        mapHelper.fit_view(extent, 16);
      }
    }
  }
}
