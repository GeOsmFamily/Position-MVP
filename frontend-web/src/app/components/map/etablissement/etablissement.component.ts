import { FicheEntrepriseComponent } from './../fiche-entreprise/fiche-entreprise.component';
import { MapHelper } from 'src/app/helpers/mapHelper';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as $ from 'jquery';
import { PositionApiService } from 'src/app/services/position-api/position-api.service';
import { ComponentHelper } from 'src/app/helpers/componentHelper';
import { MatStepper } from '@angular/material/stepper';
import { StepperSelectionEvent } from '@angular/cdk/stepper';
import { pluck } from 'rxjs/operators';

@Component({
  selector: 'app-etablissement',
  templateUrl: './etablissement.component.html',
  styleUrls: ['./etablissement.component.scss']
})
export class EtablissementComponent implements OnInit {
  firstFormGroup: FormGroup = this._formBuilder.group({
    nomEtablissement: [''],
    categorie: [''  ],
   // firstCtrl: ['', Validators.required]
  });
  secondFormGroup: FormGroup =this._formBuilder.group({
    description: [''],
   // secondCtrl:   ['', Validators.required]
  });
  adresseFormGroup: FormGroup =this._formBuilder.group({
    adresse: [''],
    codePostal: [''],
    ville: [''],
    indication: [''],
   // secondCtrl: ['', Validators.required]
  });
  contactFormGroup: FormGroup =this._formBuilder.group({
    siteInternet:[''],
    telephone1: [''],
    telephone2: [''],
    Tel1coche: [''],
    Tel2coche: [''],
   // secondCtrl: ['', Validators.required]
  });
  horaireFormGroup: FormGroup =this._formBuilder.group({
    heureOuvertlun: [''],
    heureFermelun: [''],
    heureOuvertmar: [''],
    heureFermemar: [''],
    heureOuvertmer: [''],
    heureFermemer: [''],
    heureOuvertjeu: [''],
    heureFermejeu: [''],
    heureOuvertven: [''],
    heureFermeven: [''],
    heureOuvertsam: [''],
    heureFermesam: [''],
    heureOuvertdim: [''],
    heureFermedim: [''],
    // secondCtrl: ['', Validators.required]
  });
  isEditable = false;
  checked = false;
  progressBarValue=10
 current_label="Ajouter le profil"
 current_index=-1
 @ViewChild("stepper", { static: false }) private stepper: MatStepper | undefined;


  constructor(private _formBuilder: FormBuilder) {}

  ngOnInit() {


  }

  selectionChange(event: StepperSelectionEvent):string {

    let stepLabel = event.selectedStep.label
    if (stepLabel == "Fill out your name") {
      this.current_label="Fill out your name"


    }
    return stepLabel
  }
  ngAfterViewInit() {
    this.progressBarValue=10
    this.stepper?.selectionChange
      .pipe(pluck("selectedIndex"))
      .subscribe((res: number) => {



       if(res == 0){
        this.current_label="Ajouter le profil"

       }
       else if(res==1){
         this.current_label="Ajouter la description"

       }
       else if(res==2){
        this.current_label="adresse"

       }
       else if(res==3){
        this.current_label="Ajouter le contact"

       }

       else if(res==4){
        this.current_label="Ajouter les horaires"

       }
       else {
        this.current_label="Ajouter les photos"

       }
       if(this.current_index>res)
       {
          if(this.progressBarValue>10)
          this.progressBarValue-=18

       }

       else{
        if(this.progressBarValue<100)
         this.progressBarValue+=18


       }
       this.current_index=res

      });
  }

open(){


    $('app-etablissement').css('left','0px')
  }

  close(){

    $('app-etablissement').css('left','-360px')
    this.progressBarValue=10
    this.stepper?.reset()
  }

  submit(){

    this.progressBarValue=10
    
    this.close()

    this.stepper?.reset()
  }
}


