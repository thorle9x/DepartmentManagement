import {Component, OnInit} from '@angular/core';
import {CustomersService} from '../../data/services/customers.service';
import {first} from 'rxjs/operators';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {
  customers = null;

  constructor(private customersService: CustomersService, private nzMessageService: NzMessageService) {
  }

  ngOnInit() {
    this.customersService.getAll()
      .pipe(first())
      .subscribe(
        customers => {
          this.customers = customers;
        },
        error => {
        }
      );
  }

  toggleCustomerStatus(customerId: bigint, newStatus: boolean) {
    const customer = this.customers.find(x => x.customerId === customerId);
    customer.active = newStatus;
    this.customersService.updateCustomer(customer)
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
