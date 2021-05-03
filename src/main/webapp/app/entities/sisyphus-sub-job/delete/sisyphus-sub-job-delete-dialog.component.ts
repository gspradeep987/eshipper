import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusSubJob } from '../sisyphus-sub-job.model';
import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';

@Component({
  templateUrl: './sisyphus-sub-job-delete-dialog.component.html',
})
export class SisyphusSubJobDeleteDialogComponent {
  sisyphusSubJob?: ISisyphusSubJob;

  constructor(protected sisyphusSubJobService: SisyphusSubJobService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusSubJobService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
