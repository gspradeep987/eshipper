import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';
import { WoSalesOperationalCarrierService } from './wo-sales-operational-carrier.service';

@Component({
  templateUrl: './wo-sales-operational-carrier-delete-dialog.component.html',
})
export class WoSalesOperationalCarrierDeleteDialogComponent {
  woSalesOperationalCarrier?: IWoSalesOperationalCarrier;

  constructor(
    protected woSalesOperationalCarrierService: WoSalesOperationalCarrierService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesOperationalCarrierService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesOperationalCarrierListModification');
      this.activeModal.close();
    });
  }
}
