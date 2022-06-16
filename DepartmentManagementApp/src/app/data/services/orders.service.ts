import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {Order} from '../../domain/model/order';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<Order[]>(
      `${API_URL}/order/all`
    ).pipe(
      map(orders => {
        return orders;
      })
    );
  }
}
