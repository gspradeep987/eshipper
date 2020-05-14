import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISalesAgentType } from 'app/shared/model/sales-agent-type.model';

@Component({
  selector: 'jhi-sales-agent-type-detail',
  templateUrl: './sales-agent-type-detail.component.html'
})
export class SalesAgentTypeDetailComponent implements OnInit {
  salesAgentType: ISalesAgentType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ salesAgentType }) => (this.salesAgentType = salesAgentType));
  }

  previousState(): void {
    window.history.back();
  }
}
