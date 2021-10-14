import { environment } from '../../../../environments/environment';
import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

environment:any

avaible_language=[
      {
        name: 'Français',
        code: 'fr'
      },
      {
        name: 'English',
        code: 'en'
      }
    ];

  constructor(public translate: TranslateService) {
    translate.addLangs(environment.avaible_language);
    translate.setDefaultLang('fr');
  }

  ngOnInit(): void {
    this.translate.use('fr');
  }
  //Evenement de changement de langue du géoportail
  change_language(lang:any) {
    if (lang['value']) {
      this.translate.use(lang['value']);
    }
  }

}
