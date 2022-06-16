import {Component, OnInit} from '@angular/core';
import {JwtUser} from '../../domain/model/jwt-user';
import {BasicAuthenticationService} from '../../data/services/basic-authentication.service';
import {JwtUserExtension} from '../../domain/model/jwt-user-extension';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  isCollapsed = false;
  user: JwtUser;

  constructor(private basicAuthenticationService: BasicAuthenticationService) {
    basicAuthenticationService.user.subscribe(u => this.user = u);
  }

  ngOnInit(): void {
  }

  toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  isAdmin(): boolean {
    return JwtUserExtension.isAdmin(this.user);
  }

  isUser(): boolean {
    return JwtUserExtension.isUser(this.user);
  }
}
