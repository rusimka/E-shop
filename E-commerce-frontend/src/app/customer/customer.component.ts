import { Component, OnInit } from '@angular/core';
import {ProductService} from '../product.service';
import {Observable} from 'rxjs';
import {Product} from '../product';
import {ShoppingcartService} from '../services/shoppingcart.service';
import {TokenStorageService} from '../services/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {


  products!: Observable<Product[]>;
  username!: string;

  constructor(private productService: ProductService,
              private cartService: ShoppingcartService,
              private tokenStorage: TokenStorageService,
              private router: Router) { }

  ngOnInit(): void {
    this.products = this.productService.getProductsList();
  }

  // tslint:disable-next-line:typedef
  onSubmit(productId: number) {
    const user = this.tokenStorage.getUser();
    this.username = user.username;
    this.cartService.addProductToShoppingCart(this.username, productId).subscribe(data => {
      console.log(data);
      this.router.navigate(['/cart']);
    });
  }








}
