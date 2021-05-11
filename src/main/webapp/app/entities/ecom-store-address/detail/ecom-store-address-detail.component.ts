import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreAddress } from '../ecom-store-address.model';

@Component({
  selector: 'jhi-ecom-store-address-detail',
  templateUrl: './ecom-store-address-detail.component.html',
})
export class EcomStoreAddressDetailComponent implements OnInit {
  ecomStoreAddress: IEcomStoreAddress | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreAddress }) => {
      this.ecomStoreAddress = ecomStoreAddress;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
