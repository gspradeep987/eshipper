import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShippingAddress } from '../shipping-address.model';
import { ShippingAddressService } from '../service/shipping-address.service';
import { ShippingAddressDeleteDialogComponent } from '../delete/shipping-address-delete-dialog.component';

@Component({
  selector: 'jhi-shipping-address',
  templateUrl: './shipping-address.component.html',
})
export class ShippingAddressComponent implements OnInit {
  shippingAddresses?: IShippingAddress[];
  isLoading = false;

  constructor(protected shippingAddressService: ShippingAddressService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.shippingAddressService.query().subscribe(
      (res: HttpResponse<IShippingAddress[]>) => {
        this.isLoading = false;
        this.shippingAddresses = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IShippingAddress): number {
    return item.id!;
  }

  delete(shippingAddress: IShippingAddress): void {
    const modalRef = this.modalService.open(ShippingAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shippingAddress = shippingAddress;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
