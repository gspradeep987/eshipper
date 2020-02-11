import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from './ecom-markup-tertiary.service';

@Component({
  templateUrl: './ecom-markup-tertiary-delete-dialog.component.html'
})
export class EcomMarkupTertiaryDeleteDialogComponent {
  ecomMarkupTertiary?: IEcomMarkupTertiary;

  constructor(
    protected ecomMarkupTertiaryService: EcomMarkupTertiaryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMarkupTertiaryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomMarkupTertiaryListModification');
      this.activeModal.close();
    });
  }
}
