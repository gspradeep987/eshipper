import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUser10 } from 'app/shared/model/user-10.model';
import { User10Service } from './user-10.service';

@Component({
  templateUrl: './user-10-delete-dialog.component.html'
})
export class User10DeleteDialogComponent {
  user10?: IUser10;

  constructor(protected user10Service: User10Service, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.user10Service.delete(id).subscribe(() => {
      this.eventManager.broadcast('user10ListModification');
      this.activeModal.close();
    });
  }
}
