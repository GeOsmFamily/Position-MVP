import { Component, OnInit } from '@angular/core';
import { FormBuilder , FormGroup} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss','../login/login.component.scss']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm: FormGroup = this.fb.group({

    email: [''],

  });

  constructor(private router:Router,private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  submit(){
    
  }
}
