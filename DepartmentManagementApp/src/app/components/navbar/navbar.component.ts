import {Component, OnInit} from '@angular/core';
import {JwtUser} from '../../domain/model/jwt-user';
import {BasicAuthenticationService} from '../../data/services/basic-authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  user: JwtUser;

  constructor(private basicAuthenticationService: BasicAuthenticationService) {
    basicAuthenticationService.user.subscribe(u => this.user = u);
  }

  ngOnInit(): void {
  }

  logout() {
    this.basicAuthenticationService.logout(this.user);
  }
}
