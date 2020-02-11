import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from './ecom-markup-tertiary.service';
import { EcomMarkupTertiaryDeleteDialogComponent } from './ecom-markup-tertiary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-tertiary',
  templateUrl: './ecom-markup-tertiary.component.html'
})
export class EcomMarkupTertiaryComponent implements OnInit, OnDestroy {
  ecomMarkupTertiaries?: IEcomMarkupTertiary[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomMarkupTertiaryService: EcomMarkupTertiaryService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomMarkupTertiaryService
      .query()
      .subscribe((res: HttpResponse<IEcomMarkupTertiary[]>) => (this.ecomMarkupTertiaries = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomMarkupTertiaries();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomMarkupTertiary): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomMarkupTertiaries(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomMarkupTertiaryListModification', () => this.loadAll());
  }

  delete(ecomMarkupTertiary: IEcomMarkupTertiary): void {
    const modalRef = this.modalService.open(EcomMarkupTertiaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupTertiary = ecomMarkupTertiary;
  }
}
