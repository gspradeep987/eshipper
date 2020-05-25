import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';
import { EcomOrderFullfillmentStatusService } from './ecom-order-fullfillment-status.service';

@Component({
  templateUrl: './ecom-order-fullfillment-status-delete-dialog.component.html',
})
export class EcomOrderFullfillmentStatusDeleteDialogComponent {
  ecomOrderFullfillmentStatus?: IEcomOrderFullfillmentStatus;

  constructor(
    protected ecomOrderFullfillmentStatusService: EcomOrderFullfillmentStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderFullfillmentStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomOrderFullfillmentStatusListModification');
      this.activeModal.close();
    });
  }
}
