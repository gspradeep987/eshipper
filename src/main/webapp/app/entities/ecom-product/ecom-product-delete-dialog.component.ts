import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from './ecom-product.service';

@Component({
  templateUrl: './ecom-product-delete-dialog.component.html'
})
export class EcomProductDeleteDialogComponent {
  ecomProduct?: IEcomProduct;

  constructor(
    protected ecomProductService: EcomProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomProductService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomProductListModification');
      this.activeModal.close();
    });
  }
}
