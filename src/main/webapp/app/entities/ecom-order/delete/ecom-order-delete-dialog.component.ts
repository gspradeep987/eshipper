import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrder } from '../ecom-order.model';
import { EcomOrderService } from '../service/ecom-order.service';

@Component({
  templateUrl: './ecom-order-delete-dialog.component.html',
})
export class EcomOrderDeleteDialogComponent {
  ecomOrder?: IEcomOrder;

  constructor(protected ecomOrderService: EcomOrderService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
