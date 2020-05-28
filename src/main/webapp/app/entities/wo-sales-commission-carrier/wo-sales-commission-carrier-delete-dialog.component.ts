import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';
import { WoSalesCommissionCarrierService } from './wo-sales-commission-carrier.service';

@Component({
  templateUrl: './wo-sales-commission-carrier-delete-dialog.component.html',
})
export class WoSalesCommissionCarrierDeleteDialogComponent {
  woSalesCommissionCarrier?: IWoSalesCommissionCarrier;

  constructor(
    protected woSalesCommissionCarrierService: WoSalesCommissionCarrierService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesCommissionCarrierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesCommissionCarrierListModification');
      this.activeModal.close();
    });
  }
}
