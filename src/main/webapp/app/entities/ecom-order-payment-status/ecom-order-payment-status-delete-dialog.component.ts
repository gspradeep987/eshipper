import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';
import { EcomOrderPaymentStatusService } from './ecom-order-payment-status.service';

@Component({
  templateUrl: './ecom-order-payment-status-delete-dialog.component.html',
})
export class EcomOrderPaymentStatusDeleteDialogComponent {
  ecomOrderPaymentStatus?: IEcomOrderPaymentStatus;

  constructor(
    protected ecomOrderPaymentStatusService: EcomOrderPaymentStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderPaymentStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomOrderPaymentStatusListModification');
      this.activeModal.close();
    });
  }
}
