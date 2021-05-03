import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreSync } from '../ecom-store-sync.model';
import { EcomStoreSyncService } from '../service/ecom-store-sync.service';

@Component({
  templateUrl: './ecom-store-sync-delete-dialog.component.html',
})
export class EcomStoreSyncDeleteDialogComponent {
  ecomStoreSync?: IEcomStoreSync;

  constructor(protected ecomStoreSyncService: EcomStoreSyncService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreSyncService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
