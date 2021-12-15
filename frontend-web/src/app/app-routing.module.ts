import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardGuard } from './components/auth/guard/auth-guard.guard';
import { IsauthGuardGuard } from './components/auth/guard/isauth-guard.guard';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { ResetPasswordComponent } from './components/auth/reset-password/reset-password.component';
import { EtablissementComponent } from './components/map/etablissement/etablissement.component';
import { FicheEntrepriseComponent } from './components/map/fiche-entreprise/fiche-entreprise.component';
import { MapComponent } from './components/map/map.component';

const routes: Routes = [
  {
    path: 'register',
    component: RegisterComponent,
    //canActivate: [AuthGuardGuard],
  },
  {
    path: 'resetPassword',
    component: ResetPasswordComponent,


  },

  {
    path: 'login',
    //  loadChildren: () => import('./app.module').then((mod) => mod.AppModule),
    component: LoginComponent,

  },
         { path: '', redirectTo: '/home', pathMatch: 'full' },

{
    path: 'home',
    //  loadChildren: () => import('./app.module').then((mod) => mod.AppModule),

    component: MapComponent,
    data: {
      seo: {
        title: "eeeee",
        description: "I'm a 30 year old software engineer living in Belgium.",
        shareImg: '/assets/share/about.png',
      }
    }
  },
/*
  {
    path: 'test',
    //  loadChildren: () => import('./app.module').then((mod) => mod.AppModule),
    data: {
      title: 'About',
      description:'Description Meta Tag Content',
      ogUrl: 'your og url'
    } ,
    component: FicheEntrepriseComponent,
  },*/
  {
    path: 'ajouterEtablissement',
    component: EtablissementComponent,
    canActivate: [AuthGuardGuard],
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
