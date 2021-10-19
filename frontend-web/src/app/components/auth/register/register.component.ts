import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder , FormGroup} from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss','../login/login.component.scss']
})
export class RegisterComponent implements OnInit {


  registerForm: FormGroup = this.fb.group({
    name: [''],
    email: [''],
    phone: [''],
    password: [''],
    c_password: ['']
  });

  constructor(private router:Router, private fb: FormBuilder,public authService: AuthService) {

  }

  loginPage(){
    this.router.navigate(['login'])
  }

  submit(){
    this.authService
      .register(

        this.registerForm.value.name,
        this.registerForm.value.email,
        this.registerForm.value.phone,
        this.registerForm.value.password,

      )
      .then((response: { error: boolean; msg?: string }) => {
        if (response.error) {
          // Affichage d'un message d'erreur

          console.log(response.msg)
        } else {
          console.log("hellllllp")
          console.log( this.registerForm.value.phone)
          this.router.navigate(['home']);
        }
      });

  }
  ngOnInit(): void {
  }

}
