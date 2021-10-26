import { Component, OnInit , Input} from '@angular/core';
import { Router } from '@angular/router';
import { ComponentHelper } from 'src/app/helpers/componentHelper';
import { MapHelper } from 'src/app/helpers/mapHelper';
import {
  Feature,Map

} from 'src/app/modules/ol';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MapComponent } from '../map.component';
@Component({
  selector: 'app-vertical-toolbar',
  templateUrl: './vertical-toolbar.component.html',
  styleUrls: ['./vertical-toolbar.component.scss']
})
export class VerticalToolbarComponent implements OnInit {
  @Input() map: Map | undefined;
 maphelper= new MapHelper()
  constructor(public componentHelper:ComponentHelper,public mapcomponent:MapComponent,private router:Router,public authService: AuthService) { }

  ngOnInit(): void {
  }

  userLocation(){
    // Begin geolocation
    this.mapcomponent.getPosition()
  }


  //zoom function
  zoom(type: 'plus' | 'minus') {
    if (type == 'plus') {
      this.map?.getView().setZoom(this.map?.getView().getZoom()! + 1);
    } else {
      this.map?.getView().setZoom(this.map?.getView().getZoom()! - 1);
    }
  }

  ajout_etablissement(){
    if(localStorage.getItem('access_token')){
      this.componentHelper.openEtablissement()
    }
    else
    this.router.navigate(['login'])




  }
  logout(){

    if(localStorage.getItem('access_token')!=null)
        {
          console.log(localStorage.getItem('access_token'))
          //console.log( this.authService.headers)
          this.authService.logout()
          console.log(localStorage.getItem('access_token'+0))
          localStorage.removeItem('access_token')

        }
      //  console.log(localStorage.getItem('access_token'))
    this.router.navigate(['home'])
  }
}
