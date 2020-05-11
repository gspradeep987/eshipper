import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

@Component({
  selector: 'jhi-wo-sales-operational-carrier-detail',
  templateUrl: './wo-sales-operational-carrier-detail.component.html'
})
export class WoSalesOperationalCarrierDetailComponent implements OnInit {
  woSalesOperationalCarrier: IWoSalesOperationalCarrier | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesOperationalCarrier }) => (this.woSalesOperationalCarrier = woSalesOperationalCarrier));
  }

  previousState(): void {
    window.history.back();
  }
}
