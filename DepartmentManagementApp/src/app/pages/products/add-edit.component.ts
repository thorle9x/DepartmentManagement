import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductsService} from '../../data/services/products.service';
import {first} from 'rxjs/operators';
import {CategoriesService} from '../../data/services/categories.service';
import {Category} from '../../domain/model/category';
import {ActivatedRoute, Router} from '@angular/router';
import { Product } from 'src/app/domain/model/product';

@Component({
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  form: FormGroup;
  categories: Category[] = null;
  productId: number;
  product: Product;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private productsService: ProductsService,
    private categoriesService: CategoriesService
  ) {
  }

  ngOnInit(): void {
    this.productId = this.route.snapshot.params['id'];
    this.categoriesService.getAll()
      .pipe(first())
      .subscribe(
        categories => {
          this.categories = categories;
        },
        error => {
          console.log(error);
        }
      );
    if (this.productId) {
      this.getProductById(this.productId);
    }
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      productLink: ['', Validators.required],
      price: [0, Validators.required],
      quantity: [0, Validators.required],
      categoryId: [0, Validators.required]
    });
  }

  onSubmit() {
    this.createProduct();
  }

  private createProduct() {
    return this.productsService.createProduct(this.form.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['.', {relatedTo: this.route}]);
        },
        error => {
          console.log(error);
        }
      );
  }

  compareFun = (c1: Category | string, c2: Category) => {
    if (c1) {
      return typeof c1 === 'string' ? c1 === c2.name : c1.categoryId === c2.categoryId;
    } else {
      return false;
    }
  }

  getProductById(productId: number) {
    this.productsService.getById(productId)
      .pipe(first())
      .subscribe(
        product => {
          this.product = product;
          this.form.patchValue({name: this.product.name,
                         description: this.product.description,
                         productLink: this.product.productLink,
                         price      : this.product.price,
                         quantity   : this.product.quantity
                         });
        },
        error => { 
          console.log(error);
        }
      );
  }
}
