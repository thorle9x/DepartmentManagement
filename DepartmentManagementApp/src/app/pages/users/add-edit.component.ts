import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UsersService} from '../../data/services/users.service';
import {first} from 'rxjs/operators';

@Component({
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private usersService: UsersService
  ) {
  }

  ngOnInit(): void {
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
    this.createUser();
  }

  private createUser() {
    this.usersService.createUser(this.form.value)
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
