import { Component, OnInit } from '@angular/core';
import { FormBuilder , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup = this.fb.group({
    email: [''],
    password: [''],
  });

  constructor(private router:Router,private fb: FormBuilder, public authService: AuthService) {

   }

  registerPage(){
    this.router.navigate(['register'])
  }
  resetPwdPage(){
    this.router.navigate(['resetPassword'])
  }
  submit(){


    this.authService
    .login(this.loginForm.value.email, this.loginForm.value.password)
    .then((response: { error: boolean; msg?: string }) => {
    //  console.log(response.msg)
      if (response.error) {
        // Affichage d'un message d'erreur
        console.log(response.msg)
        console.log(this.loginForm.value.email)
        this.router.navigate(['home'])
      } else {
        if(localStorage.getItem('role')!= '2')
         {
          console.log("vous n'avez pas de droit  ")
          this.router.navigate(['home']);
         }
         else
        this.router.navigate(['ajouterEtablissement']);
      }
    });

  }
  ngOnInit(): void {
  }


  open(){
    console.log("bjr")

    $('app-login').css('left','0px')
  }

  close(){

    $('app-login').css('left','-430px')
  }
}
