import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomWarehouse } from '../ecom-warehouse.model';
import { EcomWarehouseService } from '../service/ecom-warehouse.service';

@Component({
  templateUrl: './ecom-warehouse-delete-dialog.component.html',
})
export class EcomWarehouseDeleteDialogComponent {
  ecomWarehouse?: IEcomWarehouse;

  constructor(protected ecomWarehouseService: EcomWarehouseService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomWarehouseService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
