import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from './ecom-store-package-settings.service';
import { EcomStorePackageSettingsDeleteDialogComponent } from './ecom-store-package-settings-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-package-settings',
  templateUrl: './ecom-store-package-settings.component.html'
})
export class EcomStorePackageSettingsComponent implements OnInit, OnDestroy {
  ecomStorePackageSettings?: IEcomStorePackageSettings[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStorePackageSettingsService: EcomStorePackageSettingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStorePackageSettingsService
      .query()
      .subscribe((res: HttpResponse<IEcomStorePackageSettings[]>) => (this.ecomStorePackageSettings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStorePackageSettings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStorePackageSettings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStorePackageSettings(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStorePackageSettingsListModification', () => this.loadAll());
  }

  delete(ecomStorePackageSettings: IEcomStorePackageSettings): void {
    const modalRef = this.modalService.open(EcomStorePackageSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStorePackageSettings = ecomStorePackageSettings;
  }
}
