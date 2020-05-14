import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';
import { WoSalesOperationalDetailsService } from './wo-sales-operational-details.service';

@Component({
  templateUrl: './wo-sales-operational-details-delete-dialog.component.html'
})
export class WoSalesOperationalDetailsDeleteDialogComponent {
  woSalesOperationalDetails?: IWoSalesOperationalDetails;

  constructor(
    protected woSalesOperationalDetailsService: WoSalesOperationalDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesOperationalDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesOperationalDetailsListModification');
      this.activeModal.close();
    });
  }
}
