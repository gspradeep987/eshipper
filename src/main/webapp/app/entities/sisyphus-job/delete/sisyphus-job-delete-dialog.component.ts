import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusJob } from '../sisyphus-job.model';
import { SisyphusJobService } from '../service/sisyphus-job.service';

@Component({
  templateUrl: './sisyphus-job-delete-dialog.component.html',
})
export class SisyphusJobDeleteDialogComponent {
  sisyphusJob?: ISisyphusJob;

  constructor(protected sisyphusJobService: SisyphusJobService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusJobService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
