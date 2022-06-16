import {Component, OnInit} from '@angular/core';
import {ProductsService} from '../../data/services/products.service';
import {first} from 'rxjs/operators';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {
  products = null;

  constructor(private productsService: ProductsService, private nzMessageService: NzMessageService) {
  }

  ngOnInit(): void {
    this.productsService.getAll()
      .pipe(first())
      .subscribe(
        products => {
          this.products = products;
        },
        error => {
        }
      );
  }

  toggleProductStatus(productId: bigint, newStatus: boolean) {
    const product = this.products.find(x => x.productId === productId);
    product.active = newStatus;
    this.productsService.updateProduct(product)
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
}
