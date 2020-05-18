import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';
import { EcomAutomationRulesService } from './ecom-automation-rules.service';

@Component({
  templateUrl: './ecom-automation-rules-delete-dialog.component.html',
})
export class EcomAutomationRulesDeleteDialogComponent {
  ecomAutomationRules?: IEcomAutomationRules;

  constructor(
    protected ecomAutomationRulesService: EcomAutomationRulesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomAutomationRulesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomAutomationRulesListModification');
      this.activeModal.close();
    });
  }
}
