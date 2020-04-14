import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';
import { CompanyCarrierAccountService } from './company-carrier-account.service';
import { CompanyCarrierAccountDeleteDialogComponent } from './company-carrier-account-delete-dialog.component';

@Component({
  selector: 'jhi-company-carrier-account',
  templateUrl: './company-carrier-account.component.html'
})
export class CompanyCarrierAccountComponent implements OnInit, OnDestroy {
  companyCarrierAccounts?: ICompanyCarrierAccount[];
  eventSubscriber?: Subscription;

  constructor(
    protected companyCarrierAccountService: CompanyCarrierAccountService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.companyCarrierAccountService
      .query()
      .subscribe((res: HttpResponse<ICompanyCarrierAccount[]>) => (this.companyCarrierAccounts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCompanyCarrierAccounts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompanyCarrierAccount): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompanyCarrierAccounts(): void {
    this.eventSubscriber = this.eventManager.subscribe('companyCarrierAccountListModification', () => this.loadAll());
  }

  delete(companyCarrierAccount: ICompanyCarrierAccount): void {
    const modalRef = this.modalService.open(CompanyCarrierAccountDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.companyCarrierAccount = companyCarrierAccount;
  }
}
