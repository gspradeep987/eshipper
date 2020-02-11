import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from './ecom-order.service';

@Component({
  templateUrl: './ecom-order-delete-dialog.component.html'
})
export class EcomOrderDeleteDialogComponent {
  ecomOrder?: IEcomOrder;

  constructor(protected ecomOrderService: EcomOrderService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomOrderListModification');
      this.activeModal.close();
    });
  }
}
