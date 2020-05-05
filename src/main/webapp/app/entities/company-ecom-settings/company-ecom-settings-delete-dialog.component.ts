import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';
import { CompanyEcomSettingsService } from './company-ecom-settings.service';

@Component({
  templateUrl: './company-ecom-settings-delete-dialog.component.html'
})
export class CompanyEcomSettingsDeleteDialogComponent {
  companyEcomSettings?: ICompanyEcomSettings;

  constructor(
    protected companyEcomSettingsService: CompanyEcomSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.companyEcomSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('companyEcomSettingsListModification');
      this.activeModal.close();
    });
  }
}
