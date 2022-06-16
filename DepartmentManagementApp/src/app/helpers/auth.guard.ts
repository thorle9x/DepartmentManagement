import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {BasicAuthenticationService} from '../data/services/basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private basicAuthenticationService: BasicAuthenticationService) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot) {
    const user = this.basicAuthenticationService.userValue;
    if (user) {
      // check if the route is restricted by role
      if (next.data.roles) {
        if (!next.data.roles.some(r => user.roles.includes(r))) {
          this.router.navigate(['/']);
          return false;
        }
      }

      return true;
    }

    this.router.navigate(['/account/login'], {queryParams: {returnUrl: state.url}});
    return false;
  }

}
