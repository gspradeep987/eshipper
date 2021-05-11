import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreAddress } from '../ecom-store-address.model';
import { EcomStoreAddressService } from '../service/ecom-store-address.service';
import { EcomStoreAddressDeleteDialogComponent } from '../delete/ecom-store-address-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-address',
  templateUrl: './ecom-store-address.component.html',
})
export class EcomStoreAddressComponent implements OnInit {
  ecomStoreAddresses?: IEcomStoreAddress[];
  isLoading = false;

  constructor(protected ecomStoreAddressService: EcomStoreAddressService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreAddressService.query().subscribe(
      (res: HttpResponse<IEcomStoreAddress[]>) => {
        this.isLoading = false;
        this.ecomStoreAddresses = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStoreAddress): number {
    return item.id!;
  }

  delete(ecomStoreAddress: IEcomStoreAddress): void {
    const modalRef = this.modalService.open(EcomStoreAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreAddress = ecomStoreAddress;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
