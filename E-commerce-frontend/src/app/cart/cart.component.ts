import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TokenStorageService} from '../services/token-storage.service';
import {Token} from '@angular/compiler';
import {Product} from '../product';
import {ShoppingCart} from '../_models/ShoppingCart';
import {ShoppingcartService} from '../services/shoppingcart.service';
import {Observable} from 'rxjs';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products: Observable<Product[]> | undefined;
  tmpProducts!: Product[];
  id!: number;
  maxQuantity!: number;
  quantity!: number;
  subTotal!: number;
  total!: number;
  username!: string;
  userId!: number;
  cartId!: Observable<ShoppingCart>;


  @ViewChild('updateQuantityButton') updateQuantityButton!: ElementRef;

  constructor(private shoppingCartService: ShoppingcartService,
              private tokenStorageService: TokenStorageService,
              private toastr: ToastrService) {
  }


  ngOnInit(): void {
    const user = this.tokenStorageService.getUser();
    this.products = this.shoppingCartService.getShoppingCartForUser(user.id);
    // console.log(this.products);
    // @ts-ignore
    this.products.forEach((product: Product) => {
      this.maxQuantity = product.quantity;
      product.quantity = 1;
    });
  }

  // tslint:disable-next-line:typedef
  removeProduct(productId: number) {
    const user = this.tokenStorageService.getUser();
    this.userId = user.id;
    // console.log(this.userId);
    this.shoppingCartService.getCardIdForUser(this.userId).subscribe(data => {
      this.cartId = data;
      // console.log(this.cartId);
      this.removeProduct_2(this.cartId, productId);
    });


  }

  // tslint:disable-next-line:typedef
  removeProduct_2(cartId: Observable<ShoppingCart>, productId: number) {
    this.shoppingCartService.removeProductFromShoppingCart(cartId, productId).subscribe(data => {
      // console.log(data);
      this.toastr.success('You\'ve removed the product from shopping cart');
      this.ngOnInit();
    });

  }

  calculateTotalPrice(): void {
    const user = this.tokenStorageService.getUser();
    this.shoppingCartService.getShoppingCartForUser(user.id).subscribe(data => {
      this.tmpProducts = data;
      this.total = 0;
      this.subTotal = 0;
      // console.log(this.tmpProducts);
      this.tmpProducts.forEach(p => {
        this.subTotal = p.price * p.quantity;
        // console.log(this.total);
        this.total = this.total + this.subTotal;
        this.toastr.success('You\'ve successfully update the quantity');
      });


    });

  }
}




