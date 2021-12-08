import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { from, Observable, of } from 'rxjs';
import { FilterOptionInterface } from 'src/app/interfaces/filterOptionInterface';
import { HandleNominatimSearch } from './handle/handleNominatimSearch';
import {
  debounceTime,
  filter,
  tap,
  switchMap,
  map,
  catchError,
} from 'rxjs/operators';
import { merge as observerMerge } from 'rxjs';
import { ApiService } from 'src/app/services/api/api.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  form: FormGroup | undefined;

    isLoading: boolean | undefined;
    objectsIn = Object.keys;
    filterOptions: { [key: string]: Array<FilterOptionInterface> } = {

      nominatim: []
    };


  constructor(public fb: FormBuilder,  public apiService: ApiService,) {

  }

  ngOnInit(): void {
    this.isLoading=false;
    this.initialiseForm();
  }


  initialiseForm() {
    var empriseControl = new FormControl('', [Validators.minLength(2)]);
    empriseControl.valueChanges
      .pipe(
        debounceTime(500),
        filter((value) => typeof value === 'string' && value.length > 2),
        tap(() => {
          this.isLoading = true;

        }),
        switchMap((value) => {
          return observerMerge(...this.getQuerryForSerach(value)).pipe(
            map((value) => value),
            catchError((_err) => of({ error: true }))
          );
        })
      )
      .subscribe((value: any) => {
        if (value.type == 'nominatim') {
          this.filterOptions['nominatim'] =
            new HandleNominatimSearch().formatDataForTheList(value.value);
        }
        this.isLoading = false;
        this.cleanFilterOptions();
      });

    this.form = this.fb.group({
      searchWord: empriseControl,
    });
  }
  getQuerryForSerach(value: string): Observable<{
    type: string;
    error: boolean;
    value: { [key: string]: any };
  }>[] {


    var querryObs: Observable<{ type: string; error: boolean; value: { [key: string]: any; }; }>[] = [

    ];

     querryObs.push(
        from(
          this.apiService.getRequestFromOtherHost(
            'https://nominatim.openstreetmap.org/search?q=' +
              value.toString() +
              '&format=geojson&polygon_geojson=1&addressdetails=1&countrycodes=' +
              'CM'
          )
        ).pipe(
          map((val: { type: String; value: any }) => {
            return { type: 'nominatim', value: val, error: false };
          }),
          catchError((_err) =>
            of({ error: true, type: 'nominatim', value: { features: [] } })
          )
        )
      );





    return querryObs;
  }


  cleanFilterOptions() {
    for (const key in this.filterOptions) {
      if (this.filterOptions.hasOwnProperty(key)) {
        const element = this.filterOptions[key];
        if (element.length == 0) {
          this.filterOptions[key] = [];
        }
      }
    }
  }


ngAfterViewInit(): void {
  //Called after ngAfterContentInit when the component's view has been initialized. Applies to components only.
  //Add 'implements AfterViewInit' to the class.

}
login(){
  
}

  clearSearch() {
    this.form?.get('searchWord')?.patchValue('');

  }

  displayAutocompleFn(option: FilterOptionInterface) {
    if (option.typeOption == 'nominatim') {
      return new HandleNominatimSearch().displayWith(option);
    }
    return '';
  }


  optionAutocomplteSelected(selected: MatAutocompleteSelectedEvent) {
    var option: FilterOptionInterface = selected.option
      ? selected.option.value
      : undefined;
    if (option) {
      if (option.typeOption == 'nominatim') {
        new HandleNominatimSearch().optionSelected(option);
      }
    }
  }
}
