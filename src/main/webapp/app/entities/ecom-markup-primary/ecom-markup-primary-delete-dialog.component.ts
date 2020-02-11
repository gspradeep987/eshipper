import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from './ecom-markup-primary.service';

@Component({
  templateUrl: './ecom-markup-primary-delete-dialog.component.html'
})
export class EcomMarkupPrimaryDeleteDialogComponent {
  ecomMarkupPrimary?: IEcomMarkupPrimary;

  constructor(
    protected ecomMarkupPrimaryService: EcomMarkupPrimaryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupPrimaryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomMarkupPrimaryListModification');
      this.activeModal.close();
    });
  }
}
