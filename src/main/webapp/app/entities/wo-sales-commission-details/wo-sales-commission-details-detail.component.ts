import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

@Component({
  selector: 'jhi-wo-sales-commission-details-detail',
  templateUrl: './wo-sales-commission-details-detail.component.html'
})
export class WoSalesCommissionDetailsDetailComponent implements OnInit {
  woSalesCommissionDetails: IWoSalesCommissionDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionDetails }) => (this.woSalesCommissionDetails = woSalesCommissionDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
