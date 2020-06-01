import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';
import { WoSalesCommissionTransferService } from './wo-sales-commission-transfer.service';

@Component({
  templateUrl: './wo-sales-commission-transfer-delete-dialog.component.html',
})
export class WoSalesCommissionTransferDeleteDialogComponent {
  woSalesCommissionTransfer?: IWoSalesCommissionTransfer;

  constructor(
    protected woSalesCommissionTransferService: WoSalesCommissionTransferService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.woSalesCommissionTransferService.delete(id).subscribe(() => {
      this.eventManager.broadcast('woSalesCommissionTransferListModification');
      this.activeModal.close();
    });
  }
}
