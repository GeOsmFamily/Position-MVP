import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  environment: any;

  constructor(  public translate: TranslateService,) {
    this.environment = environment;
    translate.addLangs(environment.avaible_language);
    translate.setDefaultLang(environment.default_language);
   }

  ngOnInit(): void {
    this.translate.use(environment.default_language);
  }

   //Evenement de changement de langue du géoportail
   change_language(lang:any) {
    if (lang['value']) {
      this.translate.use(lang['value']);
    }
  }
}
