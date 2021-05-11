import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IShippingAddress } from '../shipping-address.model';
import { ShippingAddressService } from '../service/shipping-address.service';

@Component({
  templateUrl: './shipping-address-delete-dialog.component.html',
})
export class ShippingAddressDeleteDialogComponent {
  shippingAddress?: IShippingAddress;

  constructor(protected shippingAddressService: ShippingAddressService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shippingAddressService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
