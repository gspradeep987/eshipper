import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';

@Component({
  templateUrl: './ecom-store-shipment-settings-delete-dialog.component.html',
})
export class EcomStoreShipmentSettingsDeleteDialogComponent {
  ecomStoreShipmentSettings?: IEcomStoreShipmentSettings;

  constructor(protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreShipmentSettingsService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
