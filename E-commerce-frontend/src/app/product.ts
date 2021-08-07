import {AbstractControl} from '@angular/forms';

export class Product {
  id!: number;
  productName!: string;
  price!: number;
  quantity!: number;
  imageBase64!: string;
  description!: string;
}
