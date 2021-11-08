import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Validators } from '@angular/forms';
import * as $ from 'jquery';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loading:boolean|undefined

  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required,Validators.minLength(9),Validators.maxLength(20), Validators.email]],
    password: [''],
    // password: ['',[Validators.required, Validators.minLength(9), Validators.maxLength(20)]],

  },
  );
  submitted = false;

  constructor(private router:Router,private fb: FormBuilder, public authService: AuthService) {

   }


   get f(): { [key: string]: AbstractControl } {
    return this.loginForm.controls;
  }

  submit(): void {
    this.loading=true
    this.submitted = true;

    /*if (this.loginForm.invalid) {
      return;
    }*/

    //console.log(JSON.stringify(this.loginForm.value, null, 2));

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
        this.loading=true

          this.router.navigate(['home']);
         
      }
    });
  }
   hideloader() {

    // Setting display of spinner
    // element to none
    document.getElementById('loading')!.style.display = 'none';
}
  onReset(): void {
    this.submitted = false;
    this.loginForm.reset();
  }


  registerPage(){
    this.router.navigate(['register'])
  }
  resetPwdPage(){
    this.router.navigate(['resetPassword'])
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
