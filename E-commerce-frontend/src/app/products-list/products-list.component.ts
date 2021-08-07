import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {ProductService} from '../product.service';
import {Product} from '../product';
import {Router} from '@angular/router';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';


@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {

  products: Observable<Product[]> | undefined;
  dialogRef!: MatDialogRef<ConfirmDialogComponent>;

  constructor(private productService: ProductService,
              private router: Router,
              public dialog: MatDialog) {}

  ngOnInit(): void {
    this.products = this.productService.getProductsList();
  }

  // tslint:disable-next-line:typedef
  openConfirmationDialog(id: number) {
    this.dialogRef = this.dialog.open(ConfirmDialogComponent, {
      disableClose: false,
    });
    this.dialogRef.componentInstance.confirmMessage = 'Are you sure you want to delete?';
    this.dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.deleteProduct(id);
      }
      // this.dialogRef = null;
    });
  }

  // tslint:disable-next-line:typedef
  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe( data => {
        console.log('Deleted data is ' + data);
        this.ngOnInit();
      },
      error => console.log(error));
  }

  // tslint:disable-next-line:typedef
  productUpdate(id: number){
    this.router.navigate(['update', id]);
  }


  // tslint:disable-next-line:typedef
  addNewProduct() {
    this.router.navigate(['create-product']);
  }
}
