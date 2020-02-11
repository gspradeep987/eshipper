import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreAddress } from 'app/shared/model/ecom-store-address.model';
import { EcomStoreAddressService } from './ecom-store-address.service';

@Component({
  templateUrl: './ecom-store-address-delete-dialog.component.html'
})
export class EcomStoreAddressDeleteDialogComponent {
  ecomStoreAddress?: IEcomStoreAddress;

  constructor(
    protected ecomStoreAddressService: EcomStoreAddressService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreAddressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreAddressListModification');
      this.activeModal.close();
    });
  }
}
