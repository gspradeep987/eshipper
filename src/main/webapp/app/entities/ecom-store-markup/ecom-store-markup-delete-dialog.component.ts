import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';
import { EcomStoreMarkupService } from './ecom-store-markup.service';

@Component({
  templateUrl: './ecom-store-markup-delete-dialog.component.html'
})
export class EcomStoreMarkupDeleteDialogComponent {
  ecomStoreMarkup?: IEcomStoreMarkup;

  constructor(
    protected ecomStoreMarkupService: EcomStoreMarkupService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreMarkupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreMarkupListModification');
      this.activeModal.close();
    });
  }
}
