import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

@Component({
  selector: 'jhi-elastic-search-wo-sales-agent-detail',
  templateUrl: './elastic-search-wo-sales-agent-detail.component.html',
})
export class ElasticSearchWoSalesAgentDetailComponent implements OnInit {
  elasticSearchWoSalesAgent: IElasticSearchWoSalesAgent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticSearchWoSalesAgent }) => (this.elasticSearchWoSalesAgent = elasticSearchWoSalesAgent));
  }

  previousState(): void {
    window.history.back();
  }
}
