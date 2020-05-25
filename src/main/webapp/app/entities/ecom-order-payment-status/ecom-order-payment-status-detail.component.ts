import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';

@Component({
  selector: 'jhi-ecom-order-payment-status-detail',
  templateUrl: './ecom-order-payment-status-detail.component.html',
})
export class EcomOrderPaymentStatusDetailComponent implements OnInit {
  ecomOrderPaymentStatus: IEcomOrderPaymentStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderPaymentStatus }) => (this.ecomOrderPaymentStatus = ecomOrderPaymentStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
