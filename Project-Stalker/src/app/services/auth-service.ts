import { Injectable } from '@angular/core';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from '../model/authresponse';

@Injectable({
    providedIn:'root'
})
export class AuthService{

    constructor(private http: HttpClient, private router: Router) {}
    
  signup(user: User) {
    return this.http.post<AuthResponse>('http://localhost:8080/signup', user);
  }
  login(user: User) {
    return this.http.post<AuthResponse>('http://localhost:8080/login', user);
  }

}