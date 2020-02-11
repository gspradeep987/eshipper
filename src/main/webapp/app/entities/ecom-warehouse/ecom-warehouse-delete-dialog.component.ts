import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';
import { EcomWarehouseService } from './ecom-warehouse.service';

@Component({
  templateUrl: './ecom-warehouse-delete-dialog.component.html'
})
export class EcomWarehouseDeleteDialogComponent {
  ecomWarehouse?: IEcomWarehouse;

  constructor(
    protected ecomWarehouseService: EcomWarehouseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomWarehouseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomWarehouseListModification');
      this.activeModal.close();
    });
  }
}
