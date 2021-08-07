import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductsListComponent} from './products-list/products-list.component';
import {UpdateProductComponent} from './update-product/update-product.component';
import {CreateProductComponent} from './create-product/create-product.component';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AuthGuard} from './auth.guard';
import {ProfileComponent} from './profile/profile.component';
import {CartComponent} from './cart/cart.component';
import {CustomerComponent} from './customer/customer.component';
import {AddressComponent} from './address/address.component';
const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'products', component: ProductsListComponent},
  {path : 'update/:id', component: UpdateProductComponent},
  {path : 'create-product', component: CreateProductComponent,
  canActivate: [AuthGuard],
  data: {
    role: 'ROLE_ADMIN'
  }},
  {path: 'profile', component: ProfileComponent},
  {path: 'cart', component: CartComponent},
  {path: 'customer', component: CustomerComponent},
  {path: 'address', component: AddressComponent}




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
