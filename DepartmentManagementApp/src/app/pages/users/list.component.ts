import {Component, OnInit} from '@angular/core';
import {UsersService} from '../../data/services/users.service';
import {first} from 'rxjs/operators';
import {User} from '../../domain/model/user';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  templateUrl: 'list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  users: User[] = null;

  constructor(private usersService: UsersService, private nzMessageService: NzMessageService) {
  }

  ngOnInit() {
    this.usersService.getAll()
      .pipe(first())
      .subscribe(
        users => {
          this.users = users;
        },
        error => {
        }
      );
  }

  toggleUserStatus(userId: bigint, newStatus: boolean) {
    const user = this.users.find(x => x.id === userId);
    user.active = newStatus;
    console.log('Before request:', user);
    this.usersService.updateUser(user)
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
