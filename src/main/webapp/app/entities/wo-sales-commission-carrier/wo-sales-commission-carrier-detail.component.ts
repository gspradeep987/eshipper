import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

@Component({
  selector: 'jhi-wo-sales-commission-carrier-detail',
  templateUrl: './wo-sales-commission-carrier-detail.component.html'
})
export class WoSalesCommissionCarrierDetailComponent implements OnInit {
  woSalesCommissionCarrier: IWoSalesCommissionCarrier | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionCarrier }) => (this.woSalesCommissionCarrier = woSalesCommissionCarrier));
  }

  previousState(): void {
    window.history.back();
  }
}
