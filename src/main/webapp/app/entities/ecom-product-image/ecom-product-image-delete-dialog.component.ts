import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomProductImage } from 'app/shared/model/ecom-product-image.model';
import { EcomProductImageService } from './ecom-product-image.service';

@Component({
  templateUrl: './ecom-product-image-delete-dialog.component.html'
})
export class EcomProductImageDeleteDialogComponent {
  ecomProductImage?: IEcomProductImage;

  constructor(
    protected ecomProductImageService: EcomProductImageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomProductImageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomProductImageListModification');
      this.activeModal.close();
    });
  }
}
