import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

@Component({
  selector: 'jhi-wo-sales-agent-details-detail',
  templateUrl: './wo-sales-agent-details-detail.component.html',
})
export class WoSalesAgentDetailsDetailComponent implements OnInit {
  woSalesAgentDetails: IWoSalesAgentDetails | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesAgentDetails }) => (this.woSalesAgentDetails = woSalesAgentDetails));
  }

  previousState(): void {
    window.history.back();
  }
}
