import {Component, OnInit} from '@angular/core';
import {Category} from '../../domain/model/category';
import {CategoriesService} from '../../data/services/categories.service';
import {first} from 'rxjs/operators';
import {NzMessageService} from 'ng-zorro-antd';
import { Router } from '@angular/router';

@Component({
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {
  categories: Category[] = null;

  constructor(private router: Router, private categoriesService: CategoriesService, private nzMessageService: NzMessageService) {
  }

  ngOnInit(): void {
    this.categoriesService.getAll()
      .pipe(first())
      .subscribe(
        categories => {
          this.categories = categories;
        },
        error => {
        }
      );
  }

  toggleCategoryStatus(categoryId: bigint, newStatus: boolean) {
    const category = this.categories.find(x => x.categoryId === categoryId);
    category.active = newStatus;
    this.categoriesService.updateCategory(category)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.nzMessageService.info('Action successful');
        },
        error => {
          console.log(error);
          this.nzMessageService.error(error);
        }
      );
  }

  editCategory(categoryId: bigint) {
    console.log(categoryId);
    this.router.navigate([categoryId, 'edit']);
  }

}
