import { Component, OnInit } from '@angular/core';
import {Product} from '../product';
import {ProductService} from '../product.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Toast, ToastrService} from 'ngx-toastr';
import {File} from '@angular/compiler-cli/src/ngtsc/file_system/testing/src/mock_file_system';


@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  id!: number;
  product!: Product;

  imageSrc!: string;
  myForm = new FormGroup({
    // name: new FormControl('', [Validators.required, Validators.minLength(3)]),
    file: new FormControl('', [Validators.required]),
    fileSource: new FormControl('', [Validators.required])
  });
  // tslint:disable-next-line:prefer-const







  constructor(private route: ActivatedRoute, private productService: ProductService,
              private toastr: ToastrService,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.product = new Product();
    this.id = this.route.snapshot.params.id;
    this.productService.getProduct(this.id)
      .subscribe(data => {
        console.log(data);
        this.product = data;
      }, error => console.log(error));
  }

  // tslint:disable-next-line:typedef
  // updateProduct() {
  //   // const formData = new FormData();
  //   // @ts-ignore
  //  // formData.append('file', this.uploadForm.get('profile').value);
  //   this.productService.updateProduct(this.id, this.product)
  //     .subscribe(data => {
  //       console.log(data);
  //       this.product = new Product();
  //       this.showToaster();
  //       this.gotoList();
  //       // tslint:disable-next-line:no-shadowed-variable
  //     }, error => console.log(error));
  //
  // }

  // tslint:disable-next-line:typedef
  onSubmit() {
   // const formData = new FormData();
    // @ts-ignore
   // formData.append('image', this.selectedImage);
    this.productService.updateProduct(this.id, this.product)
      .subscribe(data => {
        console.log(data);
        // this.product = new Product();
        this.showToaster();
        this.router.navigate(['/products']);
        // tslint:disable-next-line:no-shadowed-variable
      }, error => console.log(error));

  }

  // tslint:disable-next-line:typedef
  onFileChange(event: any) {
    const reader = new FileReader();

    if (event.target.files && event.target.files.length) {
      const [file] = event.target.files;
      reader.readAsDataURL(file);

      reader.onload = () => {

        this.imageSrc = reader.result as string;

        // tslint:disable-next-line:max-line-length
        this.product.imageBase64 = this.imageSrc; // ovde se pravi glavnata promena kade sto se updatira slikata kako image base64 ( treba string da ti e)
        // 94 linija ti e za update
        this.myForm.patchValue({
          fileSource: reader.result
        });

      };
    }
  }

  // tslint:disable-next-line:typedef
  showToaster(){
    this.toastr.success('You\'ve successfully updated the product');
  }

  // tslint:disable-next-line:typedef
}
