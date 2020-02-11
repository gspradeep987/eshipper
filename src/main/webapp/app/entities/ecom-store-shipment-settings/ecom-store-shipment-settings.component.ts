import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from './ecom-store-shipment-settings.service';
import { EcomStoreShipmentSettingsDeleteDialogComponent } from './ecom-store-shipment-settings-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-shipment-settings',
  templateUrl: './ecom-store-shipment-settings.component.html'
})
export class EcomStoreShipmentSettingsComponent implements OnInit, OnDestroy {
  ecomStoreShipmentSettings?: IEcomStoreShipmentSettings[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreShipmentSettingsService
      .query()
      .subscribe((res: HttpResponse<IEcomStoreShipmentSettings[]>) => (this.ecomStoreShipmentSettings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreShipmentSettings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreShipmentSettings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreShipmentSettings(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreShipmentSettingsListModification', () => this.loadAll());
  }

  delete(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): void {
    const modalRef = this.modalService.open(EcomStoreShipmentSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
  }
}
