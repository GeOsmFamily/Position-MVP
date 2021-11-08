import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { from } from 'rxjs/internal/observable/from';
import { catchError, map } from 'rxjs/operators';
import { ApiService } from '../api/api.service';
import { UserInterface } from 'src/app/interfaces/userInterface';
import { ResetInterface } from 'src/app/interfaces/resetInterface';
import jwt_decode, { JwtPayload } from "jwt-decode";
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { environment } from 'src/environments/environment';
import { ListeCategorie } from 'src/app/interfaces/categorieInterface';


@Injectable({
  providedIn: 'root'
})
export class PositionApiService {

  url_prefix = environment.url_position_Api;

   entetes= new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*')
  .set('Authorization','Bearer ' + localStorage.getItem('access_token'));


  constructor(public router: Router,private httpClient: HttpClient) {
    }

  public GetRequestCategories(){
   const options = {  };
    if(localStorage.getItem("access_token")){

         console.log("postApi ="+ localStorage.getItem("access_token"))
      console.log(this.entetes)
    this.httpClient.get(this. url_prefix+"categories",{ headers: this.entetes .set('Authorization','Bearer ' + localStorage.getItem('access_token')?.trim()),params: new HttpParams({fromString:"limit=10"}) })
    .subscribe(
      (data)=>{
      console.log(data);
     // this.products = data;
    })  ;

    }

  }


}
