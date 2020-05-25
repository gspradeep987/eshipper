import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

@Component({
  selector: 'jhi-ecom-order-fullfillment-status-detail',
  templateUrl: './ecom-order-fullfillment-status-detail.component.html',
})
export class EcomOrderFullfillmentStatusDetailComponent implements OnInit {
  ecomOrderFullfillmentStatus: IEcomOrderFullfillmentStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ ecomOrderFullfillmentStatus }) => (this.ecomOrderFullfillmentStatus = ecomOrderFullfillmentStatus)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
