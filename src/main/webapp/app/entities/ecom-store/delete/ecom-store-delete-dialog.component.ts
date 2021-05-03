import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStore } from '../ecom-store.model';
import { EcomStoreService } from '../service/ecom-store.service';

@Component({
  templateUrl: './ecom-store-delete-dialog.component.html',
})
export class EcomStoreDeleteDialogComponent {
  ecomStore?: IEcomStore;

  constructor(protected ecomStoreService: EcomStoreService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
