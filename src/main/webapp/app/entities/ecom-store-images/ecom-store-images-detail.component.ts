import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreImages } from 'app/shared/model/ecom-store-images.model';

@Component({
  selector: 'jhi-ecom-store-images-detail',
  templateUrl: './ecom-store-images-detail.component.html',
})
export class EcomStoreImagesDetailComponent implements OnInit {
  ecomStoreImages: IEcomStoreImages | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreImages }) => (this.ecomStoreImages = ecomStoreImages));
  }

  previousState(): void {
    window.history.back();
  }
}
