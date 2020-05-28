import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalesAgentType } from 'app/shared/model/sales-agent-type.model';
import { SalesAgentTypeService } from './sales-agent-type.service';

@Component({
  templateUrl: './sales-agent-type-delete-dialog.component.html',
})
export class SalesAgentTypeDeleteDialogComponent {
  salesAgentType?: ISalesAgentType;

  constructor(
    protected salesAgentTypeService: SalesAgentTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.salesAgentTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('salesAgentTypeListModification');
      this.activeModal.close();
    });
  }
}
