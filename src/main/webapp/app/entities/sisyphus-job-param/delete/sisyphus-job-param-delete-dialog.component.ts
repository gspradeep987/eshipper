import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusJobParam } from '../sisyphus-job-param.model';
import { SisyphusJobParamService } from '../service/sisyphus-job-param.service';

@Component({
  templateUrl: './sisyphus-job-param-delete-dialog.component.html',
})
export class SisyphusJobParamDeleteDialogComponent {
  sisyphusJobParam?: ISisyphusJobParam;

  constructor(protected sisyphusJobParamService: SisyphusJobParamService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusJobParamService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
