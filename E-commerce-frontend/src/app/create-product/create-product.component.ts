import { Component, OnInit } from '@angular/core';
import {Product} from '../product';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Toast, ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../product.service';
import {DomSanitizer} from '@angular/platform-browser';


@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {


  product: Product = new Product();


  imageSrc!: string;
  myForm = new FormGroup({
    // name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });
  productForm = new FormGroup({
    productName: new FormControl(this.product.productName, [
      Validators.required]),
    description: new FormControl(this.product.description, [
      Validators.required]),
    price: new FormControl(this.product.price, [
      Validators.required]),
    quantity: new FormControl(this.product.quantity, [
      Validators.required]),
    imageBase64: new FormControl(this.product.imageBase64, [
      Validators.required])
  });
  submitted = false;



  constructor(private route: ActivatedRoute, private productService: ProductService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit(): void {

  }

  // tslint:disable-next-line:typedef
  get f() { return this.productForm.controls; }

  // tslint:disable-next-line:typedef
  onFileChange(event: any) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageSrc = reader.result as string;

        // tslint:disable-next-line:max-line-length

        this.product.imageBase64 = this.imageSrc;


        this.myForm.patchValue({
          fileSource: reader.result
        });

      };
    }
  }

  // tslint:disable-next-line:typedef
  showToaster(){
    this.toastr.success('You\'ve successfully added the product');
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
     this.submitted = true;
     this.productService
      .createProduct(this.product).subscribe(data => {
        console.log('New data for this new product is' + data);
        // this.product = new Product();
        this.showToaster();
        this.router.navigate(['/products']);
        },
       error => console.log(error));

  }
}
