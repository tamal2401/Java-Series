import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthService } from '../services/auth-service';
import { take, map } from 'rxjs/operators';


@Injectable({
  providedIn:'root'
})
export class AuthGuard implements CanActivate{

constructor(private authService : AuthService,
            private router: Router){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | import("@angular/router").UrlTree | import("rxjs").Observable<boolean | import("@angular/router").UrlTree> | Promise<boolean | import("@angular/router").UrlTree> {
    return this.authService.user.pipe(
      take(1),
      map(user => {
        if (!!user) {
          return;
        }
        return this.router.createUrlTree(['/login']);
      })
    );
  }

}
