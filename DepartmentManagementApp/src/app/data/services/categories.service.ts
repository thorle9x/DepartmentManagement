import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {API_URL} from '../app.constants';
import {map} from 'rxjs/operators';
import {Category} from '../../domain/model/category';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  constructor(private httpClient: HttpClient) {
  }

  getAll() {
    return this.httpClient.get<Category[]>(
      `${API_URL}/category/all`
    ).pipe(
      map(categories => {
        return categories;
      })
    );
  }

  createCategory(category: Category) {
    return this.httpClient.post(
      `${API_URL}/category/create`,
      category
    ).pipe(
      map(data => {
        return data;
      })
    );
  }

  getCategoryById(categoryId: bigint) {
    return this.httpClient.get<Category>(
      `${API_URL}/category/` + categoryId,
    ).pipe(
      map(category => {
        return category;
      })
    );
  }

  updateCategory(category: Category) {
    return this.httpClient.put(
      `${API_URL}/category/${category.categoryId}`,
      category
    ).pipe(
      map(data => {
        return data;
      })
    );
  }
}
