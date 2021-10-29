import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import * as $ from 'jquery';
import { PositionApiService } from 'src/app/services/position-api/position-api.service';

@Component({
  selector: 'app-etablissement',
  templateUrl: './etablissement.component.html',
  styleUrls: ['./etablissement.component.scss']
})
export class EtablissementComponent implements OnInit {

  progressbarValue=12


 


  etablissementForm=this.fb.group({
    nomEtablissement: [''],
    categorie: [''],
    Tel1: [''],
    Tel2: [''],
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
  })

  checked = false;

  constructor(private fb: FormBuilder,private positionApi:PositionApiService) { }

  ngOnInit(): void {
    this.etablissementWizard()
    this.positionApi.GetRequestCategories()

  }



  open(){
    console.log("bjr")

    $('app-etablissement').css('left','0px')
  }

  close(){

    $('app-etablissement').css('left','-360px')
  }


  etablissementWizard(){
    var current_fs:any, next_fs:any, previous_fs:any; //fieldsets
var opacity;
var current = 1;
var steps = $("fieldset").length;

setProgressBar(current);

$(".next").click(function(){

current_fs = $(this).parent();
next_fs = $(this).parent().next();
var value =$("#progressbar li").eq($("fieldset").index(next_fs)).get(0)!.textContent
console.log(value+"rrr")
console.log($("#progressbar li").eq($("fieldset").index(current_fs)).get(0)!.textContent)
$("#account").html(value!);
//Add Class Active
//$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

//show the next fieldset
next_fs.show();
//hide the current fieldset with style
current_fs.animate({opacity: 0}, {
step: function(now:any) {
// for making fielset appear animation
opacity = 1 - now;

current_fs.css({
'display': 'none',
'position': 'relative'
});
next_fs.css({'opacity': opacity});
},
duration: 500
});
setProgressBar(++current);

});

$(".previous").click(function(){


current_fs = $(this).parent();

previous_fs = $(this).parent().prev();
console.log(previous_fs)


  var value =$("#progressbar li").eq($("fieldset").index(previous_fs)).get(0)!.textContent
  $("#account").html(value!);
  console.log(value)


//Remove class active
$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");
$("#progressbar li").eq($("fieldset").index(previous_fs)).css({


  });
//show the previous fieldset
previous_fs.show();

//hide the current fieldset with style
current_fs.animate({opacity: 0}, {
step: function(now:any) {
// for making fielset appear animation
opacity = 1 - now;

current_fs.css({
'display': 'none',
'position': 'relative'
});
previous_fs.css({'opacity': opacity});
},
duration: 500
});
setProgressBar(--current);
console.log(current+ "curr")
if(current==1){
  console.log("hello")
  $("#account").html("Ajouter le profil");
}
});

function setProgressBar(curStep:any){
  var t=100 / steps
var percent = t * curStep;
percent =parseInt( percent.toFixed());
$(".progress-bar")
.css("width",percent+"%")
}

$(".submit").click(function(){
return false;
})

  }
}

