import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusClient } from '../sisyphus-client.model';
import { SisyphusClientService } from '../service/sisyphus-client.service';

@Component({
  templateUrl: './sisyphus-client-delete-dialog.component.html',
})
export class SisyphusClientDeleteDialogComponent {
  sisyphusClient?: ISisyphusClient;

  constructor(protected sisyphusClientService: SisyphusClientService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sisyphusClientService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
