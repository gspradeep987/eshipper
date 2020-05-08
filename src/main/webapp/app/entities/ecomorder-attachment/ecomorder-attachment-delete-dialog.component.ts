import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';
import { EcomorderAttachmentService } from './ecomorder-attachment.service';

@Component({
  templateUrl: './ecomorder-attachment-delete-dialog.component.html'
})
export class EcomorderAttachmentDeleteDialogComponent {
  ecomorderAttachment?: IEcomorderAttachment;

  constructor(
    protected ecomorderAttachmentService: EcomorderAttachmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomorderAttachmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomorderAttachmentListModification');
      this.activeModal.close();
    });
  }
}
