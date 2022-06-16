import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AccountRoutingModule} from './account-routing.module';
import {LayoutComponent} from './layout.component';
import {LoginComponent} from './login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NzButtonModule, NzCheckboxModule, NzFormModule, NzIconModule, NzInputModule} from 'ng-zorro-antd';
import {IconsProviderModule} from '../../icons-provider.module';


@NgModule({
  declarations: [
    LayoutComponent,
    LoginComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    AccountRoutingModule,
    ReactiveFormsModule,
    IconsProviderModule,
    NzButtonModule,
    NzInputModule,
    NzCheckboxModule,
    NzFormModule
  ]
})
export class AccountModule {
}
