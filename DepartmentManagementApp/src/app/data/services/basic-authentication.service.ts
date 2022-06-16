import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {BehaviorSubject, Observable} from 'rxjs';
import {JwtUser} from '../../domain/model/jwt-user';
import {Router} from '@angular/router';
import {LoginRequest} from './request/login-request';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {
  private userSubject: BehaviorSubject<JwtUser>;
  user: Observable<JwtUser>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<JwtUser>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): JwtUser {
    return this.userSubject.value;
  }

  executeJWTAuthenticationService(request: LoginRequest) {
    const myHeaders = new HttpHeaders().append('Authorization-Type', 'user');
    return this.http.post<JwtUser>(
      `${API_URL}/authenticate`,
      request,
      {
        headers: myHeaders
      }
    ).pipe(
      map(user => {
        localStorage.setItem('user', JSON.stringify(user));
        console.log(user);
        this.userSubject.next(user);
        return user;
      })
    );
  }

  logout(user: JwtUser) {
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/account/login']);
  }
}
