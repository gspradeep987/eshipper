import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

@Component({
  selector: 'jhi-wo-sales-agent-detail',
  templateUrl: './wo-sales-agent-detail.component.html',
})
export class WoSalesAgentDetailComponent implements OnInit {
  woSalesAgent: IWoSalesAgent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesAgent }) => (this.woSalesAgent = woSalesAgent));
  }

  previousState(): void {
    window.history.back();
  }
}
