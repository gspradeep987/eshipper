import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupPrimary } from '../ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';

@Component({
  templateUrl: './ecom-markup-primary-delete-dialog.component.html',
})
export class EcomMarkupPrimaryDeleteDialogComponent {
  ecomMarkupPrimary?: IEcomMarkupPrimary;

  constructor(protected ecomMarkupPrimaryService: EcomMarkupPrimaryService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupPrimaryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
