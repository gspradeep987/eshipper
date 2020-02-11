import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from './ecom-markup-quaternary.service';

@Component({
  templateUrl: './ecom-markup-quaternary-delete-dialog.component.html'
})
export class EcomMarkupQuaternaryDeleteDialogComponent {
  ecomMarkupQuaternary?: IEcomMarkupQuaternary;

  constructor(
    protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupQuaternaryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomMarkupQuaternaryListModification');
      this.activeModal.close();
    });
  }
}
