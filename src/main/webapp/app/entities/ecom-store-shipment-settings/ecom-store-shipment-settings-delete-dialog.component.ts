import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from './ecom-store-shipment-settings.service';

@Component({
  templateUrl: './ecom-store-shipment-settings-delete-dialog.component.html'
})
export class EcomStoreShipmentSettingsDeleteDialogComponent {
  ecomStoreShipmentSettings?: IEcomStoreShipmentSettings;

  constructor(
    protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreShipmentSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreShipmentSettingsListModification');
      this.activeModal.close();
    });
  }
}
