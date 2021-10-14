import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder , FormGroup} from '@angular/forms';
import { AuthService } from '../../../Services/auth.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss','../login/login.component.scss']
})
export class RegisterComponent implements OnInit {


  registerForm: FormGroup = this.fb.group({
    name: [''],
    email: [''],
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
        this.registerForm.value.email,

        this.registerForm.value.password,
      
        this.registerForm.value.name
      )
      .then((response: { error: boolean; msg?: string }) => {
        if (response.error) {
          // Affichage d'un message d'erreur
          console.log(response.msg)
        } else {
          this.router.navigate(['home']);
        }
      });

  }
  ngOnInit(): void {
  }

}
