import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStore } from '../ecom-store.model';

@Component({
  selector: 'jhi-ecom-store-detail',
  templateUrl: './ecom-store-detail.component.html',
})
export class EcomStoreDetailComponent implements OnInit {
  ecomStore: IEcomStore | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStore }) => {
      this.ecomStore = ecomStore;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
