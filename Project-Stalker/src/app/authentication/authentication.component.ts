import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../model/User';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth-service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

  isLoginMode : boolean = true;
  user : User = null;
  @ViewChild('authForm', { static: false }) form: NgForm;

  constructor(private route : Router,
              private authService : AuthService) { }

  ngOnInit(): void {
    console.log(this.isLoginMode);
  }

  submitForm() {
    if(!this.form.valid){
      return;
    }
    this.user = new User(this.form.control.value.userName, this.form.control.value.pwd);
    console.log(this.user);
    this.route.navigate(['profile']);
  }


  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
    this.form.reset();
  }


}
