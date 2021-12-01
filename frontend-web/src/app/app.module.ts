import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapComponent } from './components/map/map.component';
import { VerticalToolbarComponent } from './components/map/vertical-toolbar/vertical-toolbar.component';
import { MaterialModule } from './modules/material';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FicheEntrepriseComponent } from './components/map/fiche-entreprise/fiche-entreprise.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { FooterComponent } from './components/auth/footer/footer.component';
import { ResetPasswordComponent } from './components/auth/reset-password/reset-password.component';
import { HeaderComponent } from './components/map/header/header.component';
import { EtablissementComponent } from './components/map/etablissement/etablissement.component';
import { MultiTranslateHttpLoader } from 'ngx-translate-multi-http-loader';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NotifierModule } from 'angular-notifier';
import { VerticalPagePrimaryComponent } from './components/map/vertical-page-primary/vertical-page-primary.component';
import { CategoriesComponent } from './components/map/vertical-page-primary/categories/categories.component';
import { SearchComponent } from './components/map/vertical-page-primary/search/search.component';


export function HttpLoaderFactory(httpClient: HttpClient) {
  return new MultiTranslateHttpLoader(httpClient, [
    { prefix: './assets/i18n/', suffix: '.json' },
  ]);
}

@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    VerticalToolbarComponent,
    FicheEntrepriseComponent,
    LoginComponent,
    RegisterComponent,
    FooterComponent,
    ResetPasswordComponent,
    HeaderComponent,
    EtablissementComponent,
    VerticalPagePrimaryComponent,
    CategoriesComponent,
    SearchComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgbModule,


    NotifierModule.withConfig({
      position: {
        horizontal: {
          position: 'right',
          distance: 12,
        },

        vertical: {
          position: 'top',
          distance: 12,
          gap: 10,
        },
      },
    }),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
