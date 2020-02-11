import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomProductImage } from 'app/shared/model/ecom-product-image.model';

@Component({
  selector: 'jhi-ecom-product-image-detail',
  templateUrl: './ecom-product-image-detail.component.html'
})
export class EcomProductImageDetailComponent implements OnInit {
  ecomProductImage: IEcomProductImage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProductImage }) => (this.ecomProductImage = ecomProductImage));
  }

  previousState(): void {
    window.history.back();
  }
}
