import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CategoriesService} from '../../data/services/categories.service';
import {first} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import { Category } from 'src/app/domain/model/category';

@Component({
  templateUrl: './add-edit.component.html',
  styleUrls:['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  form: FormGroup;
  categoryId: bigint;
  category: Category;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private categoriesService: CategoriesService) {
  }

  ngOnInit(): void {
    var name = '';
    var categoryCode = '';
    this.categoryId = this.route.snapshot.params['id'];

    this.form = this.formBuilder.group({
      name: ['', [Validators.required]],
      categoryCode: ['', [Validators.required]]
    });

    if (this.categoryId) {
      this.getCategoryById(this.categoryId);
    }
    
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    if(this.categoryId) {
      this.updateCategory();
    } else {
      this.createCategory();
    }
  }

  private createCategory() {
    this.categoriesService.createCategory(this.form.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['.', {relativeTo: this.route}]);
        },
        error => {
          console.log(error);
        }
      );
  }

  private updateCategory() {
    this.category.name = this.form.value.name;
    this.category.categoryCode = this.form.value.categoryCode;

    this.categoriesService.updateCategory(this.category)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['.', {relativeTo: this.route}]);
        },
        error => {
          console.log(error);
        }
      );
  }

  private getCategoryById(categoryId: bigint) {
    this.categoriesService.getCategoryById(categoryId)
      .pipe(first())
      .subscribe(
        category => {
          this.category = category;
          this.form.patchValue({name: this.category.name, categoryCode: this.category.categoryCode});
        },
        error => { 
          console.log(error);
        }
      );
  }
}
