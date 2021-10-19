import { Injectable } from "@angular/core";
import { LoginComponent } from "../components/auth/login/login.component";
import { FicheEntrepriseComponent } from './../components/map/fiche-entreprise/fiche-entreprise.component';

@Injectable({
  providedIn: 'root'
})
export class ComponentHelper{

 ficheEntrepriseComponent: FicheEntrepriseComponent | undefined

 loginComponent: LoginComponent | undefined

 //display and close profilComponent
  openFicheEntreprise(){
    console.log("openFicheEntreprise")
    this.ficheEntrepriseComponent?.open()
  }
  closeFicheEntreprise(){
    this.ficheEntrepriseComponent?.close()
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
  }
}

