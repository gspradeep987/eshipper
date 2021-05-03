import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomProduct } from '../ecom-product.model';

@Component({
  selector: 'jhi-ecom-product-detail',
  templateUrl: './ecom-product-detail.component.html',
})
export class EcomProductDetailComponent implements OnInit {
  ecomProduct: IEcomProduct | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomProduct }) => {
      this.ecomProduct = ecomProduct;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
