import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupSecondary } from '../ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';

@Component({
  templateUrl: './ecom-markup-secondary-delete-dialog.component.html',
})
export class EcomMarkupSecondaryDeleteDialogComponent {
  ecomMarkupSecondary?: IEcomMarkupSecondary;

  constructor(protected ecomMarkupSecondaryService: EcomMarkupSecondaryService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupSecondaryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
