import { Data } from './../../interfaces/userInterface';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import {fromLonLat} from 'ol/proj';
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

@Injectable({
  providedIn: 'root',
})
export class PositionApiService {
  url_prefix = environment.url_position_Api;

  imagesCourousel = new Array();

  horaires = new Array();
  telephones = new Array();

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
    public apiService: ApiService
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

        var image = environment.url_image + data.data.images[0].imageUrl;

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
        feature.set('cover', data.data.cover);
        feature.set('siteInternet', data.data.siteInternet);
        feature.set('indication', data.data.batiment.indication);

        console.log("batiment geometry")
        console.log(data.data.batiment.longitude+" " +data.data.batiment.latitude)
        feature.setGeometry(new Point(fromLonLat([data.data.batiment.longitude, data.data.batiment.latitude]))
         
        );
       // new Point([, 3.866667])
        var i = 0;
        for (let index = 0; index < data.data.horaires.length; index++) {
          if (data.data.horaires[index].jour == 'Lundi' && i == 0) {
            i++;
            this.horaires?.push({
              tous_les_jours: data.data.horaires[index].jour,
              heureOuverture: data.data.horaires[index].heureOuverture,
              heureFermeture: data.data.horaires[index].heureFermeture,
            });
          }
          if (data.data.horaires[index].jour == 'Samedi') {
            this.horaires?.push({
              Samedi: data.data.horaires[index].jour,
              heureOuverture: data.data.horaires[index].heureOuverture,
              heureFermeture: data.data.horaires[index].heureFermeture,
            });
          }
          if (data.data.horaires[index].jour == 'Dimanche') {
            this.horaires?.push({
              Dimanche: data.data.horaires[index].jour,
              heureOuverture: data.data.horaires[index].heureOuverture,
              heureFermeture: data.data.horaires[index].heureFermeture,
            });
          }
          // console.log(categories[index].nom)
        }
        feature.set('horaires', this.horaires);

        var numero_whatsapp = new Array();
        for (let index = 0; index < data.data.telephones.length; index++) {
          if (data.data.telephones[index].principal == 1) {
            // this.telephones?.push({"principal":emprise.telephones[index].numero})
            feature.set(
              'telephonePrincipal',
              data.data.telephones[index].numero
            );
          } else {
            this.telephones.push(data.data.telephones[index].numero);
          }
        }
        // this.telephones?.push({"whatsapp":this.numero_whatsapp})
        feature.set('telephones', this.telephones);

        searchResultLayer.getSource().clear();

        searchResultLayer.getSource().addFeature(feature);

        var extent = feature.getGeometry()?.getExtent();

        //@ts-ignore
        mapHelper.fit_view(extent, 16);
      });
  }
}
