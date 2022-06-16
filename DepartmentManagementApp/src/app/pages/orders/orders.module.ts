import {NgModule} from '@angular/core';
import {LayoutComponent} from './layout.component';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {OrdersRoutingModule} from './orders-routing.module';
import {ListComponent} from './list.component';
import {IconsProviderModule} from '../../icons-provider.module';

@NgModule({
  declarations: [
    LayoutComponent,
    ListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    OrdersRoutingModule,
    IconsProviderModule
  ]
})
export class OrdersModule {
}
