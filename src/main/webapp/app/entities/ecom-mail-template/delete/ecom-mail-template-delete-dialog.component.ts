import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMailTemplate } from '../ecom-mail-template.model';
import { EcomMailTemplateService } from '../service/ecom-mail-template.service';

@Component({
  templateUrl: './ecom-mail-template-delete-dialog.component.html',
})
export class EcomMailTemplateDeleteDialogComponent {
  ecomMailTemplate?: IEcomMailTemplate;

  constructor(protected ecomMailTemplateService: EcomMailTemplateService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomMailTemplateService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
