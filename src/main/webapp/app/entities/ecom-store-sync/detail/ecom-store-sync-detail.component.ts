import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreSync } from '../ecom-store-sync.model';

@Component({
  selector: 'jhi-ecom-store-sync-detail',
  templateUrl: './ecom-store-sync-detail.component.html',
})
export class EcomStoreSyncDetailComponent implements OnInit {
  ecomStoreSync: IEcomStoreSync | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreSync }) => {
      this.ecomStoreSync = ecomStoreSync;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
