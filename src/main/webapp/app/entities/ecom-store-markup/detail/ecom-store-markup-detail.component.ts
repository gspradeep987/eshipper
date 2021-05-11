import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreMarkup } from '../ecom-store-markup.model';

@Component({
  selector: 'jhi-ecom-store-markup-detail',
  templateUrl: './ecom-store-markup-detail.component.html',
})
export class EcomStoreMarkupDetailComponent implements OnInit {
  ecomStoreMarkup: IEcomStoreMarkup | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreMarkup }) => {
      this.ecomStoreMarkup = ecomStoreMarkup;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
