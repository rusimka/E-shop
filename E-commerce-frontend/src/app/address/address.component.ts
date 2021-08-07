import { Component, Input, OnInit } from '@angular/core';
import { AddressService } from '../services/address.service';
import { TokenStorageService } from '../services/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import Address from '../_models/address';
import {Product} from '../product';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {


  address = new Address();
  private roles: string[] = [];
  isLoggedIn = false;
  isCustomer = false;
  isSubmitted = false;
  hasAddress = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router,
              private addressService: AddressService, private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isCustomer = this.roles.includes('ROLE_CUSTOMER');

      this.addressService.getAddressForUser(user.id).subscribe(data => {
        if (data != null) {
          this.address = data;
          this.hasAddress = true;
        } else {
          this.address = new Address();
          this.hasAddress = false;
        }
      });
    }
  }

  // when you click on the button for update check if the this user has already addres or no

  // tslint:disable-next-line:typedef
  onSubmit() {
    if (this.hasAddress) {
      this.addressService.updateAddress(this.address.id, this.address).subscribe(data => {
        this.toastrService.success('You\'ve successfully updated the address');
        this.router.navigate(['/home']);
      });
    } else {
      this.addressService
        .addAddressForUser(this.address, this.tokenStorageService.getUser().id).subscribe(data => {
        console.log(data);
        this.address = new Address();
        this.address = data;
        this.toastrService.success('You\'ve successfully updated the address');
        this.router.navigate(['/home']);

      });
    }

  }
}
