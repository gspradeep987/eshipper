import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';
import { EcomOrderSerchTypeService } from './ecom-order-serch-type.service';

@Component({
  templateUrl: './ecom-order-serch-type-delete-dialog.component.html',
})
export class EcomOrderSerchTypeDeleteDialogComponent {
  ecomOrderSerchType?: IEcomOrderSerchType;

  constructor(
    protected ecomOrderSerchTypeService: EcomOrderSerchTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomOrderSerchTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomOrderSerchTypeListModification');
      this.activeModal.close();
    });
  }
}
