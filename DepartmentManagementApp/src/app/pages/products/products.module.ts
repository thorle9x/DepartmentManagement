import {NgModule} from '@angular/core';
import {LayoutComponent} from './layout.component';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProductsRoutingModule} from './products-routing.module';
import {ListComponent} from './list.component';
import {AddEditComponent} from './add-edit.component';
import {
  NzAutocompleteModule,
  NzButtonModule,
  NzFormModule,
  NzInputModule,
  NzMessageModule,
  NzPopconfirmModule,
  NzTableModule
} from 'ng-zorro-antd';
import {IconsProviderModule} from '../../icons-provider.module';

@NgModule({
  declarations: [
    LayoutComponent,
    ListComponent,
    AddEditComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProductsRoutingModule,
    ReactiveFormsModule,
    IconsProviderModule,
    NzTableModule,
    NzButtonModule,
    NzInputModule,
    NzAutocompleteModule,
    NzFormModule,
    NzMessageModule,
    NzPopconfirmModule
  ]
})
export class ProductsModule {
}
