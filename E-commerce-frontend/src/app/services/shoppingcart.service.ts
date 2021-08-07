import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../product';
import {ShoppingCart} from '../_models/ShoppingCart';


const API_URL = 'http://localhost:8081/cart';

@Injectable({
  providedIn: 'root'
})
export class ShoppingcartService {

  // tslint:disable-next-line:no-shadowed-variable
  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  addProductToShoppingCart(userId: string, productId: number): Observable<any> {
    // @ts-ignore
    return this.http.put(`${API_URL}/${userId}/${productId}`, userId, productId);
  }

  // getProductsInCart(cartId: number): Observable<any> {
  //   // @ts-ignore
  //   return this.http.get(`${API_URL}/${cartId}`, cartId);
  // }


  getShoppingCartForUser( userId: number): Observable<any> {
    return this.http.get(`${API_URL}/${userId}`);
  }

  removeProductFromShoppingCart(cartId: Observable<ShoppingCart>, productId: number): Observable<any> {
    return this.http.delete(`${API_URL}/${cartId}/${productId}/remove-product`);
  }

  getCardIdForUser(userId: number): Observable<any> {
    return this.http.get(`${API_URL}/${userId}/get-cartId`);
  }


}
