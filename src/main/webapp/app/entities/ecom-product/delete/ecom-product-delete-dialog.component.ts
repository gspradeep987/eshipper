import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProduct } from '../ecom-product.model';
import { EcomProductService } from '../service/ecom-product.service';

@Component({
  templateUrl: './ecom-product-delete-dialog.component.html',
})
export class EcomProductDeleteDialogComponent {
  ecomProduct?: IEcomProduct;

  constructor(protected ecomProductService: EcomProductService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomProductService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
