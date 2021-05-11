import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusClasses } from '../sisyphus-classes.model';
import { SisyphusClassesService } from '../service/sisyphus-classes.service';

@Component({
  templateUrl: './sisyphus-classes-delete-dialog.component.html',
})
export class SisyphusClassesDeleteDialogComponent {
  sisyphusClasses?: ISisyphusClasses;

  constructor(protected sisyphusClassesService: SisyphusClassesService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusClassesService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
