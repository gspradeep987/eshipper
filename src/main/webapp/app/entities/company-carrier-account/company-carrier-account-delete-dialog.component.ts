import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';
import { CompanyCarrierAccountService } from './company-carrier-account.service';

@Component({
  templateUrl: './company-carrier-account-delete-dialog.component.html'
})
export class CompanyCarrierAccountDeleteDialogComponent {
  companyCarrierAccount?: ICompanyCarrierAccount;

  constructor(
    protected companyCarrierAccountService: CompanyCarrierAccountService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.companyCarrierAccountService.delete(id).subscribe(() => {
      this.eventManager.broadcast('companyCarrierAccountListModification');
      this.activeModal.close();
    });
  }
}
