import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFranchise } from 'app/shared/model/franchise.model';
import { FranchiseService } from './franchise.service';

@Component({
  templateUrl: './franchise-delete-dialog.component.html'
})
export class FranchiseDeleteDialogComponent {
  franchise?: IFranchise;

  constructor(protected franchiseService: FranchiseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.franchiseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('franchiseListModification');
      this.activeModal.close();
    });
  }
}
