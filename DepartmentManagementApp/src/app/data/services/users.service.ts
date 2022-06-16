import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {User} from '../../domain/model/user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<User[]>(
      `${API_URL}/user/all`
    ).pipe(
      map(users => {
        return users;
      })
    );
  }

  createUser(user: User) {
    return this.httpClient.post(
      `${API_URL}/user/signup`,
      user
    ).pipe(
      map(data => {
        return data;
      })
    );
  }

  updateUser(user: User) {
    return this.httpClient.put(
      `${API_URL}/user/${user.id}`,
      user
    ).pipe(
      map(data => {
        return data;
      })
    );
  }
}
