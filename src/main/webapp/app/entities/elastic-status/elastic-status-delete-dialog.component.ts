import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IElasticStatus } from 'app/shared/model/elastic-status.model';
import { ElasticStatusService } from './elastic-status.service';

@Component({
  templateUrl: './elastic-status-delete-dialog.component.html',
})
export class ElasticStatusDeleteDialogComponent {
  elasticStatus?: IElasticStatus;

  constructor(
    protected elasticStatusService: ElasticStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.elasticStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('elasticStatusListModification');
      this.activeModal.close();
    });
  }
}
