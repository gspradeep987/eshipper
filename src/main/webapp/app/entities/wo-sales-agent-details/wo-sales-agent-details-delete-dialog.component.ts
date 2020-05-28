import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';
import { WoSalesAgentDetailsService } from './wo-sales-agent-details.service';

@Component({
  templateUrl: './wo-sales-agent-details-delete-dialog.component.html',
})
export class WoSalesAgentDetailsDeleteDialogComponent {
  woSalesAgentDetails?: IWoSalesAgentDetails;

  constructor(
    protected woSalesAgentDetailsService: WoSalesAgentDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesAgentDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesAgentDetailsListModification');
      this.activeModal.close();
    });
  }
}
