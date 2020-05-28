import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

@Component({
  selector: 'jhi-wo-sales-operational-details-detail',
  templateUrl: './wo-sales-operational-details-detail.component.html',
})
export class WoSalesOperationalDetailsDetailComponent implements OnInit {
  woSalesOperationalDetails: IWoSalesOperationalDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesOperationalDetails }) => (this.woSalesOperationalDetails = woSalesOperationalDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
