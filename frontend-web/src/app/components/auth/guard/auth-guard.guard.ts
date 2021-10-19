
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class





AuthGuardGuard implements CanActivate {
  constructor(public authService: AuthService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (localStorage.getItem('access_token')!=null && localStorage.getItem('role') =='2') {
      console.log("hj "+localStorage.getItem('access_token'))
      return true
    } else {
      //console.log("ert "+localStorage.getItem('role')+ "     "+localStorage.getItem('access_token'))
     // alert("vous n'avez pas des droits")
      return this.router.navigate(['login']);
    }
  }
}
