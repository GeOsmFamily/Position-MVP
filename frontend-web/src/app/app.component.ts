import { Component } from '@angular/core';
import { Meta } from '@angular/platform-browser';

import { filter, map } from 'rxjs/operators';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-web';

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private meta:Meta) {
  }

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.router.events
    .pipe(
      filter((event: any) => event instanceof NavigationEnd),
      map(() => this.activatedRoute),
     )
    .subscribe((route: ActivatedRoute) => {
      const seo = route.snapshot.data['seo'];
      this.meta.updateTag({
        property: 'title',
        content: 'llllllllllllllll',
      });
      this.meta.updateTag({
        property: 'description',
        content: 'Retrouvez mon business sur la plateforme Position',
        //this.featurePoint?.get('nomCategorie') +',' + this.featurePoint?.get('nomSousCategorie'),
      });
      this.meta.updateTag({
        property: 'image',
        content: "eeeeeeeeeeeeeeeeeeeeeee",
      });
      this.meta.updateTag({
        property: 'url',
        content:''
      });

      // set your meta tags & title here
    });
  }
}
