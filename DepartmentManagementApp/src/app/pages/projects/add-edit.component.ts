import {Component, OnInit} from '@angular/core';
import {CustomersService} from '../../data/services/customers.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first} from 'rxjs/operators';

@Component({
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  id: bigint;
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private customersService: CustomersService
  ) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    console.log(this.id);

    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get f() {
    return this.form.controls;
  }

  onSubmit() {
    this.createCustomer();
  }

  private createCustomer() {
    this.customersService.createCustomer(this.form.value)
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
}
