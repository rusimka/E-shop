import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {Product} from './product';
import {catchError} from 'rxjs/operators';


const baseUrl = 'http://localhost:8081/products';
const urlForCreatingProduct = 'http://localhost:8081/create-product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // private baseUrl = 'http://localhost:8081/api/products';



  constructor(private http: HttpClient) { }

  // @ts-ignore
  getProductsList(): Observable<any> {
    return this.http.get(`${baseUrl}`);
  }

  getProduct(id: number): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  // tslint:disable-next-line:ban-types
  createProduct(product: Object): Observable<Object> {
    return this.http.post(`${urlForCreatingProduct}`, product);
  }


  // tslint:disable-next-line:typedef
  // handleError(error: HttpErrorResponse) {
  //   return throwError(error);
  // }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`, { responseType: 'text' });
  }

  // tslint:disable-next-line:ban-types
  // updateProduct(id: number, value: any): Observable<Object> {
  //   // @ts-ignore
  //   return this.http.put(`${this.baseUrl}/${id}`, value);
  // }

  // tslint:disable-next-line:ban-types
  updateProduct(id: number, value: any): Observable<Object> {
    // @ts-ignore
    return this.http.put(`${baseUrl}/${id}`, value);
  }
}
