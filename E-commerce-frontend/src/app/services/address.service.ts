import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


const API_URL = 'http://localhost:8081/address';


@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }

  addAddressForUser(address: object, userId: number): Observable<any> {
    return this.http.post(`${API_URL}/${userId}/create-address`, address);
  }

  updateAddress(id: number, value: any): Observable<any> {
    return this.http.put(`${API_URL}/${id}/update-address`, value);
  }

  getAddressForUser(userId: number): Observable<any> {
    return this.http.get(`${API_URL}/${userId}`);
  }
}
