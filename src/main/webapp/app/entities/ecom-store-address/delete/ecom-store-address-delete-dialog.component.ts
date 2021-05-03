import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreAddress } from '../ecom-store-address.model';
import { EcomStoreAddressService } from '../service/ecom-store-address.service';

@Component({
  templateUrl: './ecom-store-address-delete-dialog.component.html',
})
export class EcomStoreAddressDeleteDialogComponent {
  ecomStoreAddress?: IEcomStoreAddress;

  constructor(protected ecomStoreAddressService: EcomStoreAddressService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreAddressService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
