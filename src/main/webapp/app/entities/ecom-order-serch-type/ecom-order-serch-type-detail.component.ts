import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';

@Component({
  selector: 'jhi-ecom-order-serch-type-detail',
  templateUrl: './ecom-order-serch-type-detail.component.html',
})
export class EcomOrderSerchTypeDetailComponent implements OnInit {
  ecomOrderSerchType: IEcomOrderSerchType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderSerchType }) => (this.ecomOrderSerchType = ecomOrderSerchType));
  }

  previousState(): void {
    window.history.back();
  }
}
