import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-fiche-entreprise',
  templateUrl: './fiche-entreprise.component.html',
  styleUrls: ['./fiche-entreprise.component.scss']
})
export class FicheEntrepriseComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }



  open(){
    console.log("bjr")

    $('app-fiche-entreprise').css('left','0px')
  }

  close(){

    $('app-fiche-entreprise').css('left','-430px')
  }
}
