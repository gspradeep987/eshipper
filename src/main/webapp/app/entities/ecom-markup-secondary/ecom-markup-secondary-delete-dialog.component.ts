import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from './ecom-markup-secondary.service';

@Component({
  templateUrl: './ecom-markup-secondary-delete-dialog.component.html'
})
export class EcomMarkupSecondaryDeleteDialogComponent {
  ecomMarkupSecondary?: IEcomMarkupSecondary;

  constructor(
    protected ecomMarkupSecondaryService: EcomMarkupSecondaryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupSecondaryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomMarkupSecondaryListModification');
      this.activeModal.close();
    });
  }
}
