import { Injectable } from "@angular/core";
import { FicheEntrepriseComponent } from './../components/map/fiche-entreprise/fiche-entreprise.component';

@Injectable({
  providedIn: 'root'
})
export class ComponentHelper{

 ficheEntrepriseComponent: FicheEntrepriseComponent | undefined


 //display and close profilComponent
  openFicheEntreprise(){
    console.log("openFicheEntreprise")
    this.ficheEntrepriseComponent?.open()
  }
  closeFicheEntreprise(){
    this.ficheEntrepriseComponent?.close()
  }




  setComponent(component:string, comp:any){
    if(component=='FicheEntrepriseComponent'){
      this.ficheEntrepriseComponent=comp
    }
    /*if(component=='MenuComponent'){
      this.menuComponent=comp
    }*/
  }
}

