import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';
import { CompanyEcomSettingsService } from './company-ecom-settings.service';
import { CompanyEcomSettingsDeleteDialogComponent } from './company-ecom-settings-delete-dialog.component';

@Component({
  selector: 'jhi-company-ecom-settings',
  templateUrl: './company-ecom-settings.component.html'
})
export class CompanyEcomSettingsComponent implements OnInit, OnDestroy {
  companyEcomSettings?: ICompanyEcomSettings[];
  eventSubscriber?: Subscription;

  constructor(
    protected companyEcomSettingsService: CompanyEcomSettingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.companyEcomSettingsService
      .query()
      .subscribe((res: HttpResponse<ICompanyEcomSettings[]>) => (this.companyEcomSettings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCompanyEcomSettings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICompanyEcomSettings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCompanyEcomSettings(): void {
    this.eventSubscriber = this.eventManager.subscribe('companyEcomSettingsListModification', () => this.loadAll());
  }

  delete(companyEcomSettings: ICompanyEcomSettings): void {
    const modalRef = this.modalService.open(CompanyEcomSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.companyEcomSettings = companyEcomSettings;
  }
}
