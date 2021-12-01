import { Injectable } from "@angular/core";
import { LoginComponent } from "../components/auth/login/login.component";
import { EtablissementComponent } from "../components/map/etablissement/etablissement.component";
import { FicheEntrepriseComponent } from './../components/map/fiche-entreprise/fiche-entreprise.component';

@Injectable({
  providedIn: 'root'
})
export class ComponentHelper{

 ficheEntrepriseComponent: FicheEntrepriseComponent | undefined
 etablissementComponent: EtablissementComponent | undefined

 loginComponent: LoginComponent | undefined

 //display and close profilComponent
  openFicheEntreprise(featurePoint:any){
    console.log("openFicheEntreprise")
    this.ficheEntrepriseComponent?.open(featurePoint)
  }
  closeFicheEntreprise(){
    this.ficheEntrepriseComponent?.close()
  }


 //display and close etablissementComponent
 openEtablissement(){
  console.log("openEtablissement")
  this.etablissementComponent?.open()
}
closeEtablissement(){
  this.etablissementComponent?.close()
}

//display and close loginComponent
openloginPage(){
  console.log("openFicheEntreprise")
  this.loginComponent?.open()
}
closeloginPage(){
  this.loginComponent?.close()
}



  setComponent(component:string, comp:any){
    if(component=='FicheEntrepriseComponent'){
      this.ficheEntrepriseComponent=comp
    }
    if(component=='LoginComponent'){
      this.loginComponent=comp
    }
    if(component=='EtablissementComponent'){
      this.etablissementComponent=comp
    }
  }
}

