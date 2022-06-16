import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Customer} from '../../domain/model/customer';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<Customer[]>(
      `${API_URL}/customer/all`
    ).pipe(
      map(customers => {
        return customers;
      })
    );
  }

  createCustomer(customer: Customer) {
    return this.httpClient.post(
      `${API_URL}/customer/signup`,
      customer
    ).pipe(
      map(data => {
        return data;
      })
    );
  }

  updateCustomer(customer: Customer) {
    return this.httpClient.put(
      `${API_URL}/customer/${customer.customerId}`,
      customer
    ).pipe(
      map(data => {
        return data;
      })
    );
  }
}
