import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreAddress } from 'app/shared/model/ecom-store-address.model';
import { EcomStoreAddressService } from './ecom-store-address.service';
import { EcomStoreAddressDeleteDialogComponent } from './ecom-store-address-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-address',
  templateUrl: './ecom-store-address.component.html'
})
export class EcomStoreAddressComponent implements OnInit, OnDestroy {
  ecomStoreAddresses?: IEcomStoreAddress[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreAddressService: EcomStoreAddressService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreAddressService.query().subscribe((res: HttpResponse<IEcomStoreAddress[]>) => (this.ecomStoreAddresses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreAddresses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreAddress): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreAddresses(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreAddressListModification', () => this.loadAll());
  }

  delete(ecomStoreAddress: IEcomStoreAddress): void {
    const modalRef = this.modalService.open(EcomStoreAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreAddress = ecomStoreAddress;
  }
}
