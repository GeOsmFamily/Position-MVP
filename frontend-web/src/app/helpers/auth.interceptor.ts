import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { catchError, switchMap } from "rxjs/operators";
//import { TokenStorageService } from '../_services/token-storage.service';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';
import { ConditionalExpr } from '@angular/compiler';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(public  autService:AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token =  this.autService.gettoken()


    if (req.url.endsWith("/logout") || req.url.endsWith("/refresh")) {
      return next.handle(req);
    } else {

      return next.handle(req).pipe(
        catchError((error) => {
          if (error.message === 'Token expired') {
            if (error.error.message ) {
                //Genrate params for token refreshing
               
              this.autService.refreshToken()
              req = req.clone({
                setHeaders: {
                  Authorization: "Bearer " + localStorage.getItem('access_token')

                }
              });

             //REFRESH FUNTION FROM AUTH SERVICE
            }else {

                //Logout from account or do some other stuff
            }
        }
        return Observable.throw(error);

    }))


    }



  }

}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
