import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

@Component({
  selector: 'jhi-ecom-warehouse-detail',
  templateUrl: './ecom-warehouse-detail.component.html'
})
export class EcomWarehouseDetailComponent implements OnInit {
  ecomWarehouse: IEcomWarehouse | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomWarehouse }) => (this.ecomWarehouse = ecomWarehouse));
  }

  previousState(): void {
    window.history.back();
  }
}
