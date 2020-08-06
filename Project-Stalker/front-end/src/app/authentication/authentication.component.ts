import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { Observable } from 'rxjs';
import { AuthResponse } from '../model/authresponse';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss'],
})
export class AuthenticationComponent implements OnInit {
  isLoginMode: boolean = true;
  user: User = null;
  @ViewChild('authForm', { static: false }) form: NgForm;
  errorMsg: string;

  constructor(private route: Router, private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isAuthenticated.subscribe((isAuthFlag) => {
      if (isAuthFlag) {
        this.route.navigate(['profile']);
      }
    });
  }

  submitForm() {
    if (!this.form.valid) {
      return;
    }
    this.user = new User(
      this.form.control.value.userName,
      this.form.control.value.pwd
    );
    let resObs = new Observable<AuthResponse>();
    if (this.isLoginMode) {
      console.log(this.user);
      resObs = this.authService.login(this.user);
    } else {
      this.user.email = this.form.control.value.email;
      console.log(this.user);
      resObs = this.authService.signup(this.user);
    }
    resObs.subscribe(
      (res) => {
        this.route.navigate(['profile']);
      },
      (errorMsg) => {
        console.log(errorMsg);
        this.errorMsg = errorMsg;
      }
    );
    this.form.reset();
  }

  onSwitchMode() {
    this.isLoginMode = !this.isLoginMode;
    this.form.reset();
  }
}
