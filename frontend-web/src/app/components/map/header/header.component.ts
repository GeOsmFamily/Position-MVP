import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  form: FormGroup= this.fb.group({
    searchWord: [''] });

  constructor(public fb: FormBuilder) { }

  ngOnInit(): void {

  }

ngAfterViewInit(): void {
  //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
  //Add 'implements AfterViewInit' to the class.

}
login(){
  console.log(this.form.value.searchWord)
}

  clearSearch() {
    this.form?.get('searchWord')?.patchValue('');

  }
}
