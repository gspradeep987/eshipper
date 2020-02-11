import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from './shipping-address.service';

@Component({
  templateUrl: './shipping-address-delete-dialog.component.html'
})
export class ShippingAddressDeleteDialogComponent {
  shippingAddress?: IShippingAddress;

  constructor(
    protected shippingAddressService: ShippingAddressService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shippingAddressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shippingAddressListModification');
      this.activeModal.close();
    });
  }
}
