import { Component, OnInit } from '@angular/core';
import {ProductService} from '../product.service';
import {TokenStorageService} from '../services/token-storage.service';
import {Product} from '../product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products!: Product[];
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminButtons = false;
  showCustomerButtons = false;
  token!: string | null;
  customer!: string;
  admin!: string;

  constructor(private productService: ProductService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    /*this.userService.getPublicContent().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );*/
    this.productService.getProductsList().subscribe(
      data => {
        this.products = data;
        console.log(this.products);
      },
      err => {
        console.error(err);
      }
    );

   // this.isLoggedIn = !!this.tokenStorageService.getToken();

    this.token = this.tokenStorageService.getToken();
    console.log(this.token);

    if (this.token != null) {
      this.isLoggedIn = true;
      // console.log(this.isLoggedIn);
    }



    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
     // console.log(user.username);
      this.roles = user.roles;
     // console.log(this.roles);
      if (this.roles.includes('ROLE_CUSTOMER')) {
        this.customer = 'ROLE_CUSTOMER';
      }
      if (this.roles.includes('ROLE_ADMIN')) {
        this.admin = 'ROLE_ADMIN';
      }


      // this.roles = user.roles;
      // // this.showAdminButtons = this.roles.includes('ROLE_ADMIN');
      // // // this.showAdminButtons = true;
      // // this.showCustomerButtons = this.roles.includes('ROLE_CUSTOMER');
      // if (this.roles.includes('ROLE_ADMIN')) {
      //   this.showAdminButtons = true;
      // } else {
      //   this.showCustomerButtons = true;
      // }
    }
  }

}
