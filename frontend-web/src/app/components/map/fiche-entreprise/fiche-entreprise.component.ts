import { environment } from './../../../../environments/environment';
import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { Feature, Point } from 'src/app/modules/ol';
import { DeviceDetectionService } from 'src/app/services/device-detection.service';

@Component({
  selector: 'app-fiche-entreprise',
  templateUrl: './fiche-entreprise.component.html',
  styleUrls: ['./fiche-entreprise.component.scss']
})
export class FicheEntrepriseComponent implements OnInit {


 featurePoint: Feature<Point> | undefined;
 url_image=environment.url_image

  constructor(public dev:DeviceDetectionService) { }

  ngOnInit(): void {

  }



  open(featurePoint:any){
    this.featurePoint=featurePoint
    console.log(featurePoint)
    console.log("bjr")


    $('app-fiche-entreprise').css('left','0px')
  }

  close(){
console.log("closing")
    $('app-fiche-entreprise').css('left','-310px')
  }
  alert1(){
    alert("hello")
  }




}
