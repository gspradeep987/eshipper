import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';
import { WoSalesCommissionDetailsService } from './wo-sales-commission-details.service';

@Component({
  templateUrl: './wo-sales-commission-details-delete-dialog.component.html',
})
export class WoSalesCommissionDetailsDeleteDialogComponent {
  woSalesCommissionDetails?: IWoSalesCommissionDetails;

  constructor(
    protected woSalesCommissionDetailsService: WoSalesCommissionDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesCommissionDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesCommissionDetailsListModification');
      this.activeModal.close();
    });
  }
}
