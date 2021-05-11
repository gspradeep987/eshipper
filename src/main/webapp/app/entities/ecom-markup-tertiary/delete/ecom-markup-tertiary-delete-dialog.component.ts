import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupTertiary } from '../ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';

@Component({
  templateUrl: './ecom-markup-tertiary-delete-dialog.component.html',
})
export class EcomMarkupTertiaryDeleteDialogComponent {
  ecomMarkupTertiary?: IEcomMarkupTertiary;

  constructor(protected ecomMarkupTertiaryService: EcomMarkupTertiaryService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupTertiaryService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
