import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreMarkup } from '../ecom-store-markup.model';
import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';

@Component({
  templateUrl: './ecom-store-markup-delete-dialog.component.html',
})
export class EcomStoreMarkupDeleteDialogComponent {
  ecomStoreMarkup?: IEcomStoreMarkup;

  constructor(protected ecomStoreMarkupService: EcomStoreMarkupService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreMarkupService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
