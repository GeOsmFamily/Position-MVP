import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-fiche-entreprise',
  templateUrl: './fiche-entreprise.component.html',
  styleUrls: ['./fiche-entreprise.component.scss']
})
export class FicheEntrepriseComponent implements OnInit {

  imgCollection: Array<object> = [
    {
      image: 'https://loremflickr.com/g/600/400/paris',

    }, {
      image: 'https://loremflickr.com/600/400/brazil,rio',

    }]

    slides = [
      {img: "https://via.placeholder.com/600.png/09f/fff"},
      {img: "https://via.placeholder.com/600.png/021/fff"},
      {img: "https://via.placeholder.com/600.png/321/fff"},
      {img: "https://via.placeholder.com/600.png/422/fff"},
      {img: "https://via.placeholder.com/600.png/654/fff"}
    ];
    slideConfig = {"slidesToShow": 4, "slidesToScroll": 4};




  constructor() { }

  ngOnInit(): void {
  }



  open(){
    console.log("bjr")

    $('app-fiche-entreprise').css('left','0px')
  }

  close(){
console.log("closing")
    $('app-fiche-entreprise').css('left','-360px')
  }
  alert1(){
    alert("hello")
  }

  addSlide() {
    this.slides.push({img: "http://placehold.it/350x150/777777"})
  }

  removeSlide() {
    this.slides.length = this.slides.length - 1;
  }

  slickInit(e:any) {
    console.log('slick initialized');
  }

  breakpoint(e:any) {
    console.log('breakpoint');
  }

  afterChange(e:any) {
    console.log('afterChange');
  }

  beforeChange(e:any) {
    console.log('beforeChange');
  }

}
