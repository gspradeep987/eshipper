import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomOrder } from 'app/shared/model/ecom-order.model';

@Component({
  selector: 'jhi-ecom-order-detail',
  templateUrl: './ecom-order-detail.component.html'
})
export class EcomOrderDetailComponent implements OnInit {
  ecomOrder: IEcomOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrder }) => (this.ecomOrder = ecomOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
