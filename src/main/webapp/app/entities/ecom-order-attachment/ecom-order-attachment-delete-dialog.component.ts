import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';
import { EcomOrderAttachmentService } from './ecom-order-attachment.service';

@Component({
  templateUrl: './ecom-order-attachment-delete-dialog.component.html'
})
export class EcomOrderAttachmentDeleteDialogComponent {
  ecomOrderAttachment?: IEcomOrderAttachment;

  constructor(
    protected ecomOrderAttachmentService: EcomOrderAttachmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderAttachmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomOrderAttachmentListModification');
      this.activeModal.close();
    });
  }
}
