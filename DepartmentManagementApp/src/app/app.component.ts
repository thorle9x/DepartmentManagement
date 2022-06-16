import {Component} from '@angular/core';
import {JwtUser} from './domain/model/jwt-user';
import {BasicAuthenticationService} from './data/services/basic-authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isCollapsed = false;
  user: JwtUser;

  constructor(private basicAuthenticationService: BasicAuthenticationService) {
    basicAuthenticationService.user.subscribe(u => this.user = u);
  }

  logout() {
    this.basicAuthenticationService.logout(this.user);
  }
}
