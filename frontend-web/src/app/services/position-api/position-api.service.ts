import { Data } from './../../interfaces/userInterface';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { fromLonLat } from 'ol/proj';
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { from } from 'rxjs/internal/observable/from';
import { map, catchError } from 'rxjs/operators';
import { ApiService } from '../api/api.service';
import { UserInterface } from 'src/app/interfaces/userInterface';
import { ResetInterface } from 'src/app/interfaces/resetInterface';
import jwt_decode, { JwtPayload } from 'jwt-decode';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';

import { ListeCategorie } from 'src/app/interfaces/categorieInterface';
import { environment } from 'src/environments/environment';
import { Etablissement } from 'src/app/interfaces/etablissementInterface';
import { MapHelper } from 'src/app/helpers/mapHelper';
import {
  Feature,
  Geometry,
  Point,
  VectorLayer,
  VectorSource,
  VectorSourceEvent,
} from 'src/app/modules/ol';
import { ComponentHelper } from 'src/app/helpers/componentHelper';

@Injectable({
  providedIn: 'root',
})
export class PositionApiService {
  url_prefix = environment.url_position_Api;

  imagesCourousel = new Array();

  horaires = new Array();
  telephones = new Array();
  numero_whatsapp = new Array();

  entetes = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
    .set('Accept', 'application/json')
    .set(
      'X-Authorization',
      'HXEmpcwLkkFYfXUNFnwrX8RP4UxIIndpEuyTAs2Pn1J6LU6N00EWV6tcvhDyaAXA'
    );
  listeCategorie: ListeCategorie | undefined;
  Etablissement: any;
  Categories = new Array();
  geometry: Geometry | undefined;

  constructor(
    public router: Router,
    private httpClient: HttpClient,
    public apiService: ApiService,
    public componentHelper: ComponentHelper
  ) {}

  public getCategories(): any[] {
    const options = {};

    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })

    this.apiService
      .getRequest('api/categories')
      .then((data: ListeCategorie) => {
        const categories = data.data;

        for (let index = 0; index < categories.length; index++) {
          this.Categories?.push({
            id: categories[index].id,
            nom: categories[index].nom,
            logo_url: categories[index].logo_url,
          });
        }
      });
    return this.Categories;
  }

  getEtablissement(id: string) {
    const options = {};
    var mapHelper = new MapHelper();

    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })

    this.apiService
      .getRequest('api/etablissements/' + id)
      .then((data: Etablissement) => {
        this.Etablissement = data.data;

        var searchResultLayer = new VectorLayer();
        searchResultLayer = mapHelper.getLayerByName('searchResultLayer')[0];

        var cover = environment.url_image + data.data.cover;
        var imageBatiment = environment.url_image + data.data.batiment.image;
        var image = environment.url_image + data.data.images[0].imageUrl;
        this.imagesCourousel.push(imageBatiment);
        this.imagesCourousel.push(cover);
        this.imagesCourousel.push(image);
        var feature = new Feature();
        var textLabel = data.data.nom;
        var description = data.data.description;

        feature.set('imagesCarousel', this.imagesCourousel);
        feature.set('textLabel', textLabel);
        feature.set('id', data.data.id);
        feature.set(
          'logo_url',
          environment.url_image + data.data.sous_categories[0].categorie.logoUrl
        );
        feature.set('type', 'position');
        feature.set('nomCategorie', data.data.sous_categories[0].categorie.nom);
        feature.set('nomSousCategorie', data.data.sous_categories[0].nom);
        var cover = environment.url_image + data.data.cover;
        var image = environment.url_image + data.data.images[0].imageUrl;
        feature.set('imagesCarousel', this.imagesCourousel);

        feature.set('textLabel', textLabel);
        feature.set('id', data.data.id);
        feature.set(
          'logo_url',
          environment.url_image + data.data.sous_categories[0].categorie.logoUrl
        );

        feature.set('description', data.data.description);
        feature.set('type', 'position');
        feature.set(
          'adresse',
          data.data.batiment.ville +
            ', ' +
            data.data.batiment.quartier +
            ', ' +
            data.data.batiment.rue
        );
        //feature.set('nomCategorie',  data.data.nomCategorie);

        feature.set(
          'nomCategorieSousCategorie',
          data.data.sous_categories[0].nom +
            ', ' +
            data.data.sous_categories[0].nom
        );

        feature.set('cover', data.data.cover);
        feature.set('siteInternet', data.data.siteInternet);
        feature.set('indication', data.data.indicationAdresse);
        feature.set('codePostal', data.data.codePostal);
        feature.set('etage', data.data.etage);
        feature.set('motCle', data.data.autres);

        //console.log(data.data.batiment.rue+", "+data.data.batiment.ville+", bâtiment "+data.data.batiment.nom+", étage "+data.data.etage)

        feature.setGeometry(
          new Point(
            fromLonLat([
              data.data.batiment.longitude,
              data.data.batiment.latitude,
            ])
          )
        );
        // new Point([, 3.866667])
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
        data.data.horaires.sort(function sortByDay(a, b) {
          let day1 = a.jour.toLowerCase();
          let day2 = b.jour.toLowerCase();
          //@ts-ignore
          return sorter[day1] - sorter[day2];
        });

        for (let index = 0; index < data.data.horaires.length; index++) {
          data.data.horaires[index].heureOuverture=data.data.horaires[index].heureOuverture.slice(0,5)
          data.data.horaires[index].heureFermeture=data.data.horaires[index].heureFermeture.slice(0,5)

          }

        feature.set('horaires', data.data.horaires);

        var chaine_w=""
        //var numero_whatsapp = new Array();
        for (let index = 0; index < data.data.telephones.length; index++) {
          if (data.data.telephones[index].principal == 1) {
            // this.telephones?.push({"principal":emprise.telephones[index].numero})
            feature.set(
              'telephonePrincipal',
              data.data.telephones[index].numero
            );
          } else {
            this.numero_whatsapp.push(data.data.telephones[index].numero);
            chaine_w=chaine_w+data.data.telephones[index].numero+", "

          }
        }

        // this.telephones?.push({"whatsapp":this.numero_whatsapp})
        feature.set('telephones', this.telephones);
        feature.set('whatsapp', chaine_w.slice(0,-2));

        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = feature.getGeometry()?.getExtent();

        this.componentHelper.openFicheEntreprise(feature);
        //@ts-ignore
        mapHelper.fit_view(extent, 16);
        // console.log("kkkkkkkkkkkkkkkkkkkkkk")
      });
  }
}
