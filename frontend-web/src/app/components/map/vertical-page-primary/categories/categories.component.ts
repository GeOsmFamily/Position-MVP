import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';
import * as $ from 'jquery';
import { ComponentHelper } from 'src/app/helpers/componentHelper';
import { DeviceDetectionService } from 'src/app/services/device-detection.service';
import { PositionApiService } from 'src/app/services/position-api/position-api.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent implements OnInit {




  constructor(public positionApi:PositionApiService,public deviceDetector:DeviceDetectionService, public componentHelper: ComponentHelper,iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIcon('achats', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-achats.svg'));
    iconRegistry.addSvgIcon('administration', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-administration.svg'));
    iconRegistry.addSvgIcon('agriculture', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-agriculture.svg'));
    iconRegistry.addSvgIcon('alimentation', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-alimentation.svg'));
    iconRegistry.addSvgIcon('automobile', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-automobile.svg'));
    iconRegistry.addSvgIcon('banques', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-banques.svg'));
    iconRegistry.addSvgIcon('batiments', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-batiments.svg'));
    iconRegistry.addSvgIcon('bienetre', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-bienetre.svg'));
    iconRegistry.addSvgIcon('commerce', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-commerce.svg'));
    iconRegistry.addSvgIcon('communication', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-communication.svg'));
    iconRegistry.addSvgIcon('eau', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-eau.svg'));
    iconRegistry.addSvgIcon('education', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-education.svg'));
    iconRegistry.addSvgIcon('energie', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-energie.svg'));
    iconRegistry.addSvgIcon('immobilier', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-immobilier.svg'));
    iconRegistry.addSvgIcon('industrie', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-industrie.svg'));
    iconRegistry.addSvgIcon('justice', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-justice.svg'));
    iconRegistry.addSvgIcon('loisirs', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-loisirs.svg'));
    iconRegistry.addSvgIcon('nouvellesTechnologies', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-nouvelles-technologies.svg'));
    iconRegistry.addSvgIcon('petrole', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-petrole.svg'));
    iconRegistry.addSvgIcon('restosbars', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-restos&bars.svg'));
    iconRegistry.addSvgIcon('sante', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-santé.svg'));
    iconRegistry.addSvgIcon('securite', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-sécurité.svg'));
    iconRegistry.addSvgIcon('telecom', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-telecom.svg'));
    iconRegistry.addSvgIcon('textile', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-textile.svg'));
    iconRegistry.addSvgIcon('tourisme', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-tourisme.svg'));
    iconRegistry.addSvgIcon('transport', sanitizer.bypassSecurityTrustResourceUrl('assets/icon-categorie/icon-categorie-transport.svg'));






  }




  ngOnInit(): void {}
}


