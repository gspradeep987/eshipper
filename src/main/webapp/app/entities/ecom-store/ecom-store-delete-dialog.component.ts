import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from './ecom-store.service';

@Component({
  templateUrl: './ecom-store-delete-dialog.component.html',
})
export class EcomStoreDeleteDialogComponent {
  ecomStore?: IEcomStore;

  constructor(protected ecomStoreService: EcomStoreService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreListModification');
      this.activeModal.close();
    });
  }
}
