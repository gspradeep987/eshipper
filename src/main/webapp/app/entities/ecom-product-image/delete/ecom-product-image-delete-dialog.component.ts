import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProductImage } from '../ecom-product-image.model';
import { EcomProductImageService } from '../service/ecom-product-image.service';

@Component({
  templateUrl: './ecom-product-image-delete-dialog.component.html',
})
export class EcomProductImageDeleteDialogComponent {
  ecomProductImage?: IEcomProductImage;

  constructor(protected ecomProductImageService: EcomProductImageService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomProductImageService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
