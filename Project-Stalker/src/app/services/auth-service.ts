import { Injectable } from '@angular/core';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../model/authresponse';
import { BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { LoggedInUser } from '../model/LoogedInUser';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user = new BehaviorSubject<LoggedInUser>(null);

  constructor(private http: HttpClient, private router: Router) { }

  signup(user: User) {
    return this.http
      .post<AuthResponse>('http://localhost:8080/signup', user)
      .pipe(tap(response => {
        this.handleAuthentication(response);
      }));
  }

  login(user: User) {
    return this.http
      .post<AuthResponse>('http://localhost:8080/login', user)
      .pipe(tap(response => {
        this.handleAuthentication(response);
      }));
  }

  handleAuthentication(response: AuthResponse) {
    const expirationDate = new Date(new Date().getTime()+response.expiresIn);
    const user = new LoggedInUser(response.user, response.email, response.token, expirationDate);
    this.user.next(user);
  }
}