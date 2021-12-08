import { Data } from './../../interfaces/userInterface';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
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


@Injectable({
  providedIn: 'root',
})
export class PositionApiService {
  url_prefix = environment.url_position_Api;

  entetes = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Access-Control-Allow-Origin', '*')
    .set('Accept', 'application/json')
    .set('X-Authorization','HXEmpcwLkkFYfXUNFnwrX8RP4UxIIndpEuyTAs2Pn1J6LU6N00EWV6tcvhDyaAXA');
  listeCategorie: ListeCategorie | undefined;
Etablissement:any
  Categories=new Array()

  constructor(
    public router: Router,
    private httpClient: HttpClient,
    public apiService: ApiService
  ) {}

  public getCategories():any[] {
    const options = {};

    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })

    this.apiService
      .getRequest('api/categories')
      .then((data: ListeCategorie) => {
        const categories=data.data

        console.log(data.data)
        for (let index = 0; index < categories.length; index++){
          this.Categories?.push({id:categories[index].id,nom:categories[index].nom,logo_url:categories[index].logo_url})
         // console.log(categories[index].nom)
        }
console.log(this.Categories)


      });
      return this.Categories
  }

  public getEtablissement(id:string):any  {
    const options = {};

    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })

    this.apiService
      .getRequest('api/etablissements/'+id)
      .then((data) => {
          this.Etablissement=data.data
          console.log(data)
        
          console.log(data.data.cover)
          console.log(this.Etablissement)
               }



      );
               return this.Etablissement
  }
}
