import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from './shipping-address.service';
import { ShippingAddressDeleteDialogComponent } from './shipping-address-delete-dialog.component';

@Component({
  selector: 'jhi-shipping-address',
  templateUrl: './shipping-address.component.html'
})
export class ShippingAddressComponent implements OnInit, OnDestroy {
  shippingAddresses?: IShippingAddress[];
  eventSubscriber?: Subscription;

  constructor(
    protected shippingAddressService: ShippingAddressService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.shippingAddressService.query().subscribe((res: HttpResponse<IShippingAddress[]>) => (this.shippingAddresses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShippingAddresses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShippingAddress): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShippingAddresses(): void {
    this.eventSubscriber = this.eventManager.subscribe('shippingAddressListModification', () => this.loadAll());
  }

  delete(shippingAddress: IShippingAddress): void {
    const modalRef = this.modalService.open(ShippingAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shippingAddress = shippingAddress;
  }
}
