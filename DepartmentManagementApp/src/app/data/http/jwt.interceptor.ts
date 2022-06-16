import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BasicAuthenticationService} from '../services/basic-authentication.service';
import {API_URL} from '../app.constants';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private basicAuthenticationService: BasicAuthenticationService) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const user = this.basicAuthenticationService.userValue;
    const isLoggedIn = user && user.token;
    const isApiUrl = request.url.startsWith(API_URL);
    if (isLoggedIn && isApiUrl) {
      request = request.clone(
        {
          setHeaders: {
            AuthorizationType: 'user',
            AuthorizationId: `${user.username}`,
            Authorization: `Bearer ${user.token}`
          }
        }
      );
    }
    return next.handle(request);
  }
}
