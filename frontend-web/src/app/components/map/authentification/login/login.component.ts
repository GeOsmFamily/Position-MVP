import { Component, OnInit } from '@angular/core';
import { FormBuilder , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../Services/auth.service';

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
      if (response.error) {
        // Affichage d'un message d'erreur
        console.log("eerr")
        console.log(this.loginForm.value.email)
      } else {
        this.router.navigate(['home']);
      }
    });

  }
  ngOnInit(): void {
  }

}
