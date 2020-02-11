import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

@Component({
  selector: 'jhi-ecom-store-shipment-settings-detail',
  templateUrl: './ecom-store-shipment-settings-detail.component.html'
})
export class EcomStoreShipmentSettingsDetailComponent implements OnInit {
  ecomStoreShipmentSettings: IEcomStoreShipmentSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreShipmentSettings }) => (this.ecomStoreShipmentSettings = ecomStoreShipmentSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
