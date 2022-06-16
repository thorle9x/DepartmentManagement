import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {BasicAuthenticationService} from '../../data/services/basic-authentication.service';

@Component({templateUrl: 'layout.component.html'})
export class LayoutComponent {
  constructor(private router: Router, private basicAuthenticationService: BasicAuthenticationService) {

  }
}
