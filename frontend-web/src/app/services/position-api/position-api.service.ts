import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { from } from 'rxjs/internal/observable/from';
import { catchError, map } from 'rxjs/operators';
import { ApiService } from '../api/api.service';
import { UserInterface } from 'src/app/interfaces/userInterface';
import { ResetInterface } from 'src/app/interfaces/resetInterface';
import jwt_decode, { JwtPayload } from 'jwt-decode';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { environment } from 'src/environments/environment';
import { ListeCategorie } from 'src/app/interfaces/categorieInterface';

@Injectable({
  providedIn: 'root',
})
export class PositionApiService {
  url_prefix = environment.url_backend;

  entetes = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', 'application/json')
    .set(
      'X-Authorization',
      'LMqyHOewo4jcZsJgz24zcUYUOHt6b36EkvdoHvwhERBoqOAZeGWpiE3GRU9sjumc'
    );

  constructor(public router: Router, private httpClient: HttpClient) {}

  public getCategories() {
    const options = {};

    //this.httpClient.get(this. url_prefix+"api/categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })

    this.httpClient
      .get(this.url_prefix + 'api/categories', { headers: this.entetes })
      .subscribe((data) => {
        console.log(data);
        // this.products = data;
      });
  }
}
