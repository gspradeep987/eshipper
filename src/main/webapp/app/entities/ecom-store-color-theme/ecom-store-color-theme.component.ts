import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from './ecom-store-color-theme.service';
import { EcomStoreColorThemeDeleteDialogComponent } from './ecom-store-color-theme-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-color-theme',
  templateUrl: './ecom-store-color-theme.component.html'
})
export class EcomStoreColorThemeComponent implements OnInit, OnDestroy {
  ecomStoreColorThemes?: IEcomStoreColorTheme[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreColorThemeService: EcomStoreColorThemeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreColorThemeService
      .query()
      .subscribe((res: HttpResponse<IEcomStoreColorTheme[]>) => (this.ecomStoreColorThemes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreColorThemes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreColorTheme): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreColorThemes(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreColorThemeListModification', () => this.loadAll());
  }

  delete(ecomStoreColorTheme: IEcomStoreColorTheme): void {
    const modalRef = this.modalService.open(EcomStoreColorThemeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreColorTheme = ecomStoreColorTheme;
  }
}
