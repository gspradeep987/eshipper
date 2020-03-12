import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreSync } from 'app/shared/model/ecom-store-sync.model';
import { EcomStoreSyncService } from './ecom-store-sync.service';

@Component({
  templateUrl: './ecom-store-sync-delete-dialog.component.html'
})
export class EcomStoreSyncDeleteDialogComponent {
  ecomStoreSync?: IEcomStoreSync;

  constructor(
    protected ecomStoreSyncService: EcomStoreSyncService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreSyncService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreSyncListModification');
      this.activeModal.close();
    });
  }
}
