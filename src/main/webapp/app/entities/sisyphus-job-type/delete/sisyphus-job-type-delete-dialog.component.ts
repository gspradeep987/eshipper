import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusJobType } from '../sisyphus-job-type.model';
import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';

@Component({
  templateUrl: './sisyphus-job-type-delete-dialog.component.html',
})
export class SisyphusJobTypeDeleteDialogComponent {
  sisyphusJobType?: ISisyphusJobType;

  constructor(protected sisyphusJobTypeService: SisyphusJobTypeService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusJobTypeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
