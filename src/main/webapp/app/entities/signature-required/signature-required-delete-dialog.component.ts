import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISignatureRequired } from 'app/shared/model/signature-required.model';
import { SignatureRequiredService } from './signature-required.service';

@Component({
  templateUrl: './signature-required-delete-dialog.component.html'
})
export class SignatureRequiredDeleteDialogComponent {
  signatureRequired?: ISignatureRequired;

  constructor(
    protected signatureRequiredService: SignatureRequiredService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.signatureRequiredService.delete(id).subscribe(() => {
      this.eventManager.broadcast('signatureRequiredListModification');
      this.activeModal.close();
    });
  }
}
