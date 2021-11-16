import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { from, Observable, of } from 'rxjs';
import { FilterOptionInterface } from 'src/app/interfaces/filterOptionInterface';
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
import { HandleNominatimSearch } from '../../header/handle/handleNominatimSearch';
import { HandleSousCategoriesSearch } from '../../header/handle/handleSousCategoriesSearch';
import { Fill, Icon, Stroke, Style, VectorLayer, VectorSource } from 'src/app/modules/ol';
import { environment } from 'src/environments/environment';
import { DataHelper } from 'src/app/helpers/dataHelper';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {


  form: FormGroup | undefined;

  isLoading: boolean | undefined;
  objectsIn = Object.keys;
  filterOptions: { [key: string]: Array<FilterOptionInterface> } = {

    nominatim: [],
    souscategories:[],
    etablissements:[]
  };

//@ts-ignore
   searchResultLayer: VectorLayer = new VectorLayer({
    source: new VectorSource(),
    style: (feature) => {
      var textLabel;
      var textStyle = {
        font: '15px Calibri,sans-serif',
        fill: new Fill({ color: '#000' }),
        stroke: new Stroke({ color: '#000', width: 1 }),
        padding: [10, 10, 10, 10],
        offsetX: 0,
        offsetY: 0,
      };
      if (feature.get('textLabel')) {
        textLabel = feature.get('textLabel');
        //@ts-ignore
        textStyle['text'] = textLabel;
        if (feature.getGeometry()?.getType() == 'Point') {
          textStyle.offsetY = 40;
          //@ts-ignore
          textStyle['backgroundFill'] = new Fill({ color: '#fff' });
        }
      }

      var color = '#ade36b';
      return new Style({
        fill: new Fill({
          color: [
            DataHelper.hexToRgb(color)!.r,
            DataHelper.hexToRgb(color)!.g,
            DataHelper.hexToRgb(color)!.b,
            0.5,
          ],
        }),
        stroke: new Stroke({
          color: environment.primaryColor,
          width: 6,
        }),
        image: new Icon({
          scale: 0.7,
          src: '/assets/icones/marker-search.png',
        }),
//@ts-ignore
        text: new Text(textStyle),
      });
    },
    //@ts-ignore
    type_layer: 'searchResultLayer',
    nom: 'searchResultLayer',
  });


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
        console.log('loading');
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
      else if (value.type == 'souscategories') {
          console.log(1)
    console.log(value)
        this.filterOptions['souscategories'] =
          new HandleSousCategoriesSearch().formatDataForTheList(value.value.data)
      }
      else if(value.type == 'etablissements'){
     /*   this.filterOptions['etablissements'] =
        new HandleEtablissementSearch().formatDataForTheList(value.value.data);*/

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
    from(
      this.apiService.getRequest('search/souscategories?q='+value.toString())
    ).pipe(
      map((val: { type: String; value: any }) => {
        return { type: 'souscategories', value: val, error: false };
      }),
      catchError((_err) =>
        of({ error: _err, type: 'souscategories', value: { features: [] } })
      )
    ),
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

    querryObs.push(
      from(
        this.apiService.getRequest('search/etablissements?q='+value.toString() )
      ).pipe(
        map((val: { type: String; value: any }) => {
          return { type: 'etablissements', value: val, error: false };
        }),
        catchError((_err) =>
          of({ error: true, type: 'etablissements', value: { features: [] } })
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
console.log(this.form?.value.searchWord)
}

clearSearch() {
  this.form?.get('searchWord')?.patchValue('');

}

displayAutocompleFn(option: FilterOptionInterface) {
  if (option.typeOption == 'nominatim') {
    return new HandleNominatimSearch().displayWith(option);
  }
  else if (option.typeOption == 'souscategories') {
    return new HandleSousCategoriesSearch().displayWith(option);
  }
  else if (option.typeOption == 'etablissements') {
  //  return new HandleEtablissementsSearch().displayWith(option);
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
    else if (option.typeOption == 'souscategories') {
      new HandleSousCategoriesSearch().optionSelected(option);
    }
    else if (option.typeOption == 'etablissements') {
    //  new HandleEtablissementsSearch().optionSelected(option);
    }
  }
}

}
