import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupQuaternary } from '../ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';

@Component({
  templateUrl: './ecom-markup-quaternary-delete-dialog.component.html',
})
export class EcomMarkupQuaternaryDeleteDialogComponent {
  ecomMarkupQuaternary?: IEcomMarkupQuaternary;

  constructor(protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupQuaternaryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
