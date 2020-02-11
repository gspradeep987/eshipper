import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';
import { EcomMailTemplateService } from './ecom-mail-template.service';

@Component({
  templateUrl: './ecom-mail-template-delete-dialog.component.html'
})
export class EcomMailTemplateDeleteDialogComponent {
  ecomMailTemplate?: IEcomMailTemplate;

  constructor(
    protected ecomMailTemplateService: EcomMailTemplateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMailTemplateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomMailTemplateListModification');
      this.activeModal.close();
    });
  }
}
