import { BehaviorSubject, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { LoggedInUser } from '../model/loogedInUser';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { AuthResponse } from '../model/authresponse';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn:'root'
})
export class AuthService {

  user = new BehaviorSubject<LoggedInUser>(null);
  isAuthenticated = new BehaviorSubject<Boolean>(false);

  constructor(private http: HttpClient, private router: Router) {}

  signup(user: User) {
    return this.http
      .post<AuthResponse>('http://localhost:8080/signup', user)
      .pipe(
        tap((response) => {
          this.handleAuthentication(response);
        })
      );
  }

  login(user: User) {
    return this.http
      .post<AuthResponse>('http://localhost:8080/login', user)
      .pipe(
        catchError(this.handleError),
        tap((response) => {
          this.handleAuthentication(response);
        })
      );
  }

  handleAuthentication(response: AuthResponse) {
    // const expirationDate = new Date(new Date().getTime() + response.expiresIn);
    const user = new LoggedInUser(
      response.user,
      response.email,
      response.token
    );
    this.user.next(user);
    localStorage.setItem('stalker', JSON.stringify(user));
  }

  logOut() {
    this.user.next(null);
    this.isAuthenticated.next(false);
    this.router.navigate(['/login']);
  }

  autoLogin() {
    const temp: {
      user: string;
      email: string;
      token: string;
    } = JSON.parse(localStorage.getItem('stalker'));

    if (!temp) {
      return;
    }

    const loggedUser = new LoggedInUser(temp.user, temp.email, temp.token);
    this.user.next(loggedUser);
    this.isAuthenticated.next(true);
  }

  handleError(error:HttpErrorResponse){
    return throwError(error.message);
  }
}
