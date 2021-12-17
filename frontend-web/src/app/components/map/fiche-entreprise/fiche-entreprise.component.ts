import { ComponentHelper } from 'src/app/helpers/componentHelper';
import { environment } from './../../../../environments/environment';
import { Component, OnInit, Output, EventEmitter  } from '@angular/core';
import * as $ from 'jquery';
import { Feature, Point } from 'src/app/modules/ol';
import { DeviceDetectionService } from 'src/app/services/device-detection.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  ActivatedRoute,
  Router,
  NavigationStart,
  NavigationEnd,
} from '@angular/router';
import { Meta, Title } from '@angular/platform-browser';
import { filter, map, mergeMap } from 'rxjs/operators';
import { ParentChildServiceService } from 'src/app/services/parentChildService/parent-child-service.service';

@Component({
  selector: 'app-fiche-entreprise',
  templateUrl: './fiche-entreprise.component.html',
  styleUrls: ['./fiche-entreprise.component.scss'],
})
export class FicheEntrepriseComponent implements OnInit {
  featurePoint: Feature<Point> | undefined;
  url_image = environment.url_image;
  // url_position = environment.url_frontend;
  url_demo = environment.url_demo;
  images = [];

  @Output() newItemEvent = new EventEmitter<Feature<Point>>();
  addNewItem(featurePoint: Feature<Point> ) {
    this.newItemEvent.emit(featurePoint);
  }
  constructor(private ds:ParentChildServiceService ,
    private tit:Title,
    private meta: Meta,
    private router: Router,
    private modalService: NgbModal,
    private activatedRoute: ActivatedRoute,
    private componentHelper: ComponentHelper
  ) {}

  ngOnInit(): void {


  }



  shareLink() {


    var url_share =
      environment.url_demo +
      '?etablissements=' +
      this.featurePoint?.get('id') +
      ',16';
    this.componentHelper.openSocialShare(url_share, 7);
  }

  ngAfterViewInit(): void {

    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.



  }

  sendData(){
    //this.ds.sendData(this.featurePoint!);
  }

  open(featurePoint: any) {
    this.featurePoint = featurePoint;
    this.ds.sendData([this.featurePoint?.get('textLabel'),environment.url_image+this.featurePoint?.get('cover')]);
   // this.ds.sendData();


    this.images = this.featurePoint?.get('imagesCarousel');

    $('app-fiche-entreprise').css('left', '0px');
  }

  close() {
    $('app-fiche-entreprise').css('left', '-350px');
  }
  alert1() {
    alert('hello');
  }

  openContacterEntreprise() {
    /*  console.log(this.featurePoint)
    const modalRef = this.modalService.open(ContacterEntrepriseComponent);
    modalRef.componentInstance.my_modal_title =this.featurePoint?.get("telephones")[0].principal;
    modalRef.componentInstance.my_modal_content = 'I am your content';
    */
    // alert(this.featurePoint?.get('telephones')[0].principal);
  }
}
