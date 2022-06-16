import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {Product} from '../../domain/model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<Product[]>(
      `${API_URL}/product/all`
    ).pipe(
      map(products => {
        return products;
      })
    );
  }

  getById(productId: number) {
    return this.httpClient.get<Product>(
      `${API_URL}/product/${productId}`
    ).pipe(
      map(product => {
        return product;
      })
    );
  }

  createProduct(product: Product) {
    return this.httpClient.post(
      `${API_URL}/product/create`,
      product
    ).pipe(
      map(data => {
        return data;
      })
    );
  }

  updateProduct(product: Product) {
    return this.httpClient.put(
      `${API_URL}/product/${product.productId}`,
      product
    ).pipe(
      map(data => {
        return data;
      })
    );
  }
}
