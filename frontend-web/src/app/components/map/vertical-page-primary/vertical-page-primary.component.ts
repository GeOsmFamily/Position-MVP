import { SearchComponent } from './search/search.component';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-vertical-page-primary',
  templateUrl: './vertical-page-primary.component.html',
  styleUrls: ['./vertical-page-primary.component.scss']
})
export class VerticalPagePrimaryComponent implements OnInit {

IsLoading=false

 @ViewChild(SearchComponent)
 set greetApp(search: SearchComponent) {
  this.IsLoading = search.isLoading
  
};

  constructor() { }

  ngOnInit(): void {
  }


  countChangedHandler(count:boolean) {
    this.IsLoading = count;

  }

}
