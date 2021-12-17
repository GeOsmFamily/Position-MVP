import { Component } from '@angular/core';
import { Meta, Title } from '@angular/platform-browser';

import { filter, map, mergeMap } from 'rxjs/operators';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { ParentChildServiceService } from './services/parentChildService/parent-child-service.service';
import { Feature, Point } from './modules/ol';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend-web';
  subscription: Subscription;

  featurePoint:string | undefined
  constructor(private ds:ParentChildServiceService ,private tit: Title,private router: Router, private activatedRoute: ActivatedRoute, private meta:Meta) {
    this.subscription = this.ds.getData().subscribe(x => {
                      this.featurePoint = x[0];
                     // console.log("rrrrrr +"+ this.featurePoint)
                      this.meta.updateTag({
                        name: 'description',
                        content: "Retrouvez mon entreprise sur la plateforme Position en suivant ce lien"
                     })
                     this.meta.updateTag({
                      name: 'keywords',
                      content: x[2]
                   })
                     this.meta.updateTag({
                      name: 'og:image',
                      content: x[1]
                   })
                   this.meta.updateTag({
                    name: 'title',
                    content: x[0]
                 })
                     this.tit.setTitle(x[0]);
    });

  }


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.



  }ngAfterViewInit(): void {
    //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
    //Add 'implements AfterViewInit' to the class.
    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd),
      map(() => this.activatedRoute),
      map((route) => {
         while (route.firstChild) route = route.firstChild;
         return route;
      }),
      filter((route) => route.outlet === 'primary'),
      mergeMap((route) => route.data)
   )
   .subscribe((event) => {


      //Updating Description tag dynamically with title
      //this._seoService.updateDescription(event['title'] + event['description'])
   });
  }
}
