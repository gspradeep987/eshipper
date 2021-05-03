import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IJobType } from '../job-type.model';
import { JobTypeService } from '../service/job-type.service';

@Component({
  templateUrl: './job-type-delete-dialog.component.html',
})
export class JobTypeDeleteDialogComponent {
  jobType?: IJobType;

  constructor(protected jobTypeService: JobTypeService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.jobTypeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
