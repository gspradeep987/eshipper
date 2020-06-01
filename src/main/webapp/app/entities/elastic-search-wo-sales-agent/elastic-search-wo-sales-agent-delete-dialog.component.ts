import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';
import { ElasticSearchWoSalesAgentService } from './elastic-search-wo-sales-agent.service';

@Component({
  templateUrl: './elastic-search-wo-sales-agent-delete-dialog.component.html',
})
export class ElasticSearchWoSalesAgentDeleteDialogComponent {
  elasticSearchWoSalesAgent?: IElasticSearchWoSalesAgent;

  constructor(
    protected elasticSearchWoSalesAgentService: ElasticSearchWoSalesAgentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.elasticSearchWoSalesAgentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('elasticSearchWoSalesAgentListModification');
      this.activeModal.close();
    });
  }
}
