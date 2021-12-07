import { Component, OnInit , Input} from '@angular/core';
import { Router } from '@angular/router';
import { ComponentHelper } from 'src/app/helpers/componentHelper';
import { MapHelper } from 'src/app/helpers/mapHelper';
import {
  Feature,Map

} from 'src/app/modules/ol';
import { AuthService } from 'src/app/services/auth/auth.service';
import { MapComponent } from '../map.component';
import { NotifierService } from 'angular-notifier';
import { PositionApiService } from 'src/app/services/position-api/position-api.service';

@Component({
  selector: 'app-vertical-toolbar',
  templateUrl: './vertical-toolbar.component.html',
  styleUrls: ['./vertical-toolbar.component.scss']
})
export class VerticalToolbarComponent implements OnInit {
  @Input() map: Map | undefined;
 maphelper= new MapHelper()

 private readonly notifier: NotifierService;

  constructor(public positionApi:PositionApiService, notifierService: NotifierService,public componentHelper:ComponentHelper,public mapcomponent:MapComponent,private router:Router,public authService: AuthService) {
    this.notifier = notifierService;
   }

  ngOnInit(): void {
    this.positionApi.getCategories()
  }

  ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
   /* if(localStorage.getItem('role')=='2'){
      console.log("role= "+localStorage.getItem('role'))

    }*/
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

   /* if(localStorage.getItem('access_token') ==null){
      console.log("no log in)")
      this.router.navigate(['login'])
    }
    else{

      if(localStorage.getItem('role')=='2'){
        console.log("role= "+localStorage.getItem('role'))

        this.componentHelper.openEtablissement()
      }
      else {
        console.log("role= "+localStorage.getItem('role'))
        this.notifier.notify('error', 'vous n\'avez pas ce droit');
      }

    }
*/
this.componentHelper.openEtablissement()





  }
  logout(){

    /*if(localStorage.getItem('access_token')!=null)
        {
          console.log(localStorage.getItem('access_token'))
          //console.log( this.authService.headers)
          this.authService.logout()
          //console.log(localStorage.getItem('access_token'+0))
          localStorage.removeItem('access_token')


        }
      //  console.log(localStorage.getItem('access_token'))
    this.router.navigate(['home'])
  }*/
}
}
