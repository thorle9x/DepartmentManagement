import {RouterModule, Routes} from '@angular/router';
import {LayoutComponent} from './layout.component';
import {ListComponent} from './list.component';
import {NgModule} from '@angular/core';
import {AddEditComponent} from './add-edit.component';

const routes: Routes = [
  {
    path: '', component: LayoutComponent, children: [
      {path: '', component: ListComponent},
      {path: 'add', component: AddEditComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule {
}
