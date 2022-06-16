import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProjectsRoutingModule} from './projects-routing.module';
import {LayoutComponent} from './layout.component';
import {ListComponent} from './list.component';
import {AddEditComponent} from './add-edit.component';
import {NzButtonModule, NzFormModule, NzInputModule, NzMessageModule, NzPopconfirmModule, NzTableModule} from 'ng-zorro-antd';
import {IconsProviderModule} from '../../icons-provider.module';

@NgModule(
  {
    declarations: [
      LayoutComponent,
      ListComponent,
      AddEditComponent
    ],
    imports: [
      CommonModule,
      FormsModule,
      ProjectsRoutingModule,
      ReactiveFormsModule,
      IconsProviderModule,
      NzTableModule,
      NzButtonModule,
      NzFormModule,
      NzInputModule,
      NzMessageModule,
      NzPopconfirmModule
    ]
  }
)
export class ProjectsModule {

}
