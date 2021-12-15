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
import { ShareButtonsModule } from 'ngx-sharebuttons/buttons';
import { ShareIconsModule } from 'ngx-sharebuttons/icons';
import { SocialShareComponent } from './components/social-share/social-share/social-share.component';
import { ShareButtonsConfig } from 'ngx-sharebuttons';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import {
  provideAnalytics,
  getAnalytics,
  ScreenTrackingService,
  UserTrackingService,
} from '@angular/fire/analytics';
import { providePerformance, getPerformance } from '@angular/fire/performance';
import { AngularFireModule } from '@angular/fire/compat';
import { AngularFireAnalyticsModule } from '@angular/fire/compat/analytics';
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';

const customConfig: ShareButtonsConfig = {
  include: ['copy', 'facebook', 'twitter', 'linkedin', 'messenger', 'whatsapp'],
  exclude: ['tumblr', 'stumble', 'vk'],
  theme: 'circles-dark',
  gaTracking: true,
  twitterAccount: 'twitterUsername',
};

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
    SocialShareComponent,
  ],
  imports: [
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    AngularFireAnalyticsModule,
    ShareButtonsModule.withConfig(customConfig),
    ShareIconsModule,
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgbModule,
    FontAwesomeModule,

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
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the app is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000',
    }),
    /*   provideFirebaseApp(() => initializeApp(environment.firebase)),
    provideAnalytics(() => getAnalytics()),
    providePerformance(() => getPerformance()),*/
  ],
  providers: [ScreenTrackingService, UserTrackingService],
  bootstrap: [AppComponent],
})
export class AppModule {}
