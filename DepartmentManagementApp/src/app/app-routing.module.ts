import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './pages/home/home.component';
import {AuthGuard} from './helpers/auth.guard';

const accountModule = () => import('./pages/login/account.module').then(x => x.AccountModule);
const usersModule = () => import('./pages/users/users.module').then(x => x.UsersModule);
const customersModule = () => import('./pages/customers/customers.module').then(x => x.CustomersModule);
const productsModule = () => import('./pages/products/products.module').then(x => x.ProductsModule);
const ordersModule = () => import('./pages/orders/orders.module').then(x => x.OrdersModule);
const categoriesModule = () => import('./pages/categories/categories.module').then(x => x.CategoriesModule);

const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'account', loadChildren: accountModule},
  {path: 'users', loadChildren: usersModule, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN']}},
  {path: 'customers', loadChildren: customersModule, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN']}},
  {path: 'orders', loadChildren: ordersModule, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN', 'ROLE_USER']}},
  {path: 'products', loadChildren: productsModule, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN', 'ROLE_USER']}},
  {path: 'categories', loadChildren: categoriesModule, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN', 'ROLE_USER']}},

  // otherwise redirect to home
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
