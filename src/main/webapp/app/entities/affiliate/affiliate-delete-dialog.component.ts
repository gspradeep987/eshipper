import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAffiliate } from 'app/shared/model/affiliate.model';
import { AffiliateService } from './affiliate.service';

@Component({
  templateUrl: './affiliate-delete-dialog.component.html',
})
export class AffiliateDeleteDialogComponent {
  affiliate?: IAffiliate;

  constructor(protected affiliateService: AffiliateService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.affiliateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('affiliateListModification');
      this.activeModal.close();
    });
  }
}
