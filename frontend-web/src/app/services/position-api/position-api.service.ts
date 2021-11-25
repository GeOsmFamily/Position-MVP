import { Injectable } from '@angular/core';

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
import { environment } from 'src/environments/environment';
import { ListeCategorie } from 'src/app/interfaces/categorieInterface';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class PositionApiService {
  url_prefix = environment.url_position_Api ;

  entetes = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', 'application/json')
    .set(
      'X-Authorization ',
      'sssy1jcp4yAzGt6C0IpgkTqXb9zz9wpbm2QXK92ghHeSFvnqwRRxi7j2ZZf28tDg'
    );

     // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'X-Authorization':
      'sssy1jcp4yAzGt6C0IpgkTqXb9zz9wpbm2QXK92ghHeSFvnqwRRxi7j2ZZf28tDg'


    })
  }

  listeCategorie: ListeCategorie | undefined;

  constructor(public router: Router, private httpClient: HttpClient) {}

  public getCategories() {
    const options = {};

    console.log("categories ")
    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })


    this.httpClient
      .get(this.url_prefix + 'api/categories', this.httpOptions)
       .subscribe(
    (data) => {
     // this.tutorials = data;
      console.log(data);
    },
    (    error: any) => {
      console.log(error);
    });
  }

}
