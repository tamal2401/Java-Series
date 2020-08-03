import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isAuthenticated:boolean=true;

  constructor(private authService : AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onLogout(){
    this.authService.user.subscribe(user => {
      this.authService.user.next(null);
      this.isAuthenticated = false;
      this.router.navigate(['/login']);
    });
  }
}
