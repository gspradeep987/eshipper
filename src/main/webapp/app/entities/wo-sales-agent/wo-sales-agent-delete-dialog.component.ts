import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';

@Component({
  templateUrl: './wo-sales-agent-delete-dialog.component.html',
})
export class WoSalesAgentDeleteDialogComponent {
  woSalesAgent?: IWoSalesAgent;

  constructor(
    protected woSalesAgentService: WoSalesAgentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesAgentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesAgentListModification');
      this.activeModal.close();
    });
  }
}
