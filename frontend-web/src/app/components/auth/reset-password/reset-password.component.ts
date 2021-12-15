import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder , FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss','../login/login.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm: FormGroup = this.fb.group({

    email: ['', [Validators.required,Validators.minLength(9),Validators.maxLength(20), Validators.email]],


  });
  submitted = false;

  constructor(private router:Router,private fb: FormBuilder,  public authService: AuthService) { }

  ngOnInit(): void {
  }


  get f(): { [key: string]: AbstractControl } {
    return this.resetPasswordForm.controls;
  }
  onReset(): void {
    this.submitted = false;
    this.resetPasswordForm.reset();
  }

  submit(){

    this.submitted = true;

    if (this.resetPasswordForm.invalid) {
      return;
    }

   

    this.authService
    .reset(this.resetPasswordForm.value.email)
    .then((response: { error: boolean; msg?: string }) => {

      if (response.error) {
        // Affichage d'un message d'erreur

        //this.router.navigate(['home'])
      } else {

        this.router.navigate(['login']);
      }
    });
  }
}
