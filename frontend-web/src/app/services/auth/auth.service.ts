import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { HttpHeaders } from '@angular/common/http';
import { from } from 'rxjs/internal/observable/from';
import { catchError, map } from 'rxjs/operators';
import { ApiService } from '../api/api.service';
import { UserInterface } from 'src/app/interfaces/userInterface';
import { ResetInterface } from 'src/app/interfaces/resetInterface';
import jwt_decode, { JwtPayload } from "jwt-decode";
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  headers: HttpHeaders = new HttpHeaders({});
  constructor(public apiService: ApiService,public router: Router) {
    this.headers.append('Content-Type', 'application/x-www-form-urlencoded');
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Access-Control-Allow-Origin', '*');
    this.headers.append('No-Auth', 'True');
    this.headers.append('Accept', 'application/json');

  }

  storeToken(token: string) {
    localStorage.setItem('access_token', token);

  }
  storeRefreshToken(token: string) {
    localStorage.setItem('refreshToken', token);

  }

  getRefreshToken(token: string) {
    localStorage.getItem('refreshToken');

  }


  storeRole(role: string) {
    localStorage.setItem('role', role);

  }

  getRole(){
    return localStorage.getItem('role')
  }

  gettoken(){
    return localStorage.getItem('access_token')
  }
  getUserConnect(): Observable<UserInterface> {
    return from(this.apiService.getRequest('getuser')).pipe(
      map((userInterface: UserInterface) => {
        return userInterface;
      }),
      catchError((err) => {
        throw new Error(err);
      })
    );
  }


  removeToken() {
    let removeToken = localStorage.removeItem('access_token');

    if (removeToken == null) {
      console.log("logout "+removeToken)
     // this.router.navigate(['home']);
    }

  }

  login(email: string, pwd: string): Promise<{ error: boolean; msg?: string }> {
    return new Promise((resolve, reject) => {
      from(
        this.apiService.post_requete('auth/login', {
          email: email,
          password: pwd,
        })
      )
        .pipe(
          catchError((err) => {
            resolve({
              error: true,
              msg: '',
            });
            // return ''
            throw new Error(err);
          })
        )
        .subscribe(
          (userInterface: any) => {
            console.log(userInterface.access_token!)
            this.storeToken(userInterface.access_token!);
           // localStorage.setItem('access_token',userInterface.access_token!)
            //console.log(localStorage.getItem('access_token'))
          // var decoded = jwt_decode(userInterface.access_token!);
         var decodedToken: any = jwtDecode(userInterface.access_token!);
            this.storeRole(decodedToken.roles_id[0])
          // let jsonObj = JSON.parse(decodedHeader)
             console.log(decodedToken.roles_id[0]);
           // console.log("decode "+decoded)
            resolve({
              error: false,
            });
          },
          (err) => {
            reject({
              resolve: true,
            });
          }
        );
    });
  }

  register(
    name: string,
    email: string,
    phone:number,
    pwd: string,



  ): Promise<{ error: boolean; msg?: string }> {
    return new Promise((resolve, reject) => {
      from(
        this.apiService.post_requete('register/create', {
          name: name,
          email: email,
          phone:phone,
          password: pwd,

        })
      )
        .pipe(
          catchError((err) => {

            resolve({
              error: true,
              msg: '',
            });
            // return ''
            throw new Error(err);
          })
        )
        .subscribe(
          (userInterface: UserInterface) => {
            resolve({
              error: false,
            });
          },
          (err) => {
            reject({
              resolve: true,
            });
          }
        );
    });
  }

  reset(email: string): Promise<{ error: boolean; msg?: string }> {
    return new Promise((resolve, reject) => {
      from(
        this.apiService.post_requete('password/email', {
          email: email,
        })
      )
        .pipe(
          catchError((err) => {
            resolve({
              error: true,
              msg: '',
            });
            // return ''
            throw new Error(err);
          })
        )
        .subscribe(
          (resetInterface: ResetInterface) => {
            resolve({
              error: false,
            });
          },
          (err) => {
            reject({
              resolve: true,
            });
          }
        );
    });
  }


  refreshToken() : Promise<{ error: boolean; msg?: string }> {
    return new Promise((resolve, reject) => {
      from(
        this.apiService.post_requete('auth/refresh', {

        })
      )
        .pipe(
          catchError((err) => {
            resolve({
              error: true,
              msg: '',
            });
            // return ''
            throw new Error(err);
          })
        )
        .subscribe(
          (data: any) => {

              this.storeRefreshToken(data.access_token)
              this.storeToken(data.access_token)
            resolve({
              error: false,
            });
          },
          (err) => {
            reject({
              resolve: true,
            });
          }
        );
    });
  }


  logout(): Promise<{ error: boolean; msg?: string }> {
    return new Promise((resolve, reject) => {
      from(
        this.apiService.logout_post_requete('auth/logout',{}
        )

      )
        .pipe(
          catchError((err) => {
            resolve({
              error: true,
              msg: '',
            });
            // return ''
            throw new Error(err);
          })
        )
        .subscribe(
          (data: any) => {

           console.log(data)
            resolve({
              error: false,
            });
          },
          (err) => {
            reject({
              resolve: true,
            });
          }
        );
    });
  }
}
