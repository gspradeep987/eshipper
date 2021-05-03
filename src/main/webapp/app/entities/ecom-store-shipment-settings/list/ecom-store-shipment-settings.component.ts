import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';
import { EcomStoreShipmentSettingsDeleteDialogComponent } from '../delete/ecom-store-shipment-settings-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-shipment-settings',
  templateUrl: './ecom-store-shipment-settings.component.html',
})
export class EcomStoreShipmentSettingsComponent implements OnInit {
  ecomStoreShipmentSettings?: IEcomStoreShipmentSettings[];
  isLoading = false;

  constructor(protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreShipmentSettingsService.query().subscribe(
      (res: HttpResponse<IEcomStoreShipmentSettings[]>) => {
        this.isLoading = false;
        this.ecomStoreShipmentSettings = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStoreShipmentSettings): number {
    return item.id!;
  }

  delete(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): void {
    const modalRef = this.modalService.open(EcomStoreShipmentSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
