import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';
import { EcomStoreMarkupService } from './ecom-store-markup.service';
import { EcomStoreMarkupDeleteDialogComponent } from './ecom-store-markup-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-markup',
  templateUrl: './ecom-store-markup.component.html'
})
export class EcomStoreMarkupComponent implements OnInit, OnDestroy {
  ecomStoreMarkups?: IEcomStoreMarkup[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreMarkupService: EcomStoreMarkupService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreMarkupService.query().subscribe((res: HttpResponse<IEcomStoreMarkup[]>) => (this.ecomStoreMarkups = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreMarkups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreMarkup): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreMarkups(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreMarkupListModification', () => this.loadAll());
  }

  delete(ecomStoreMarkup: IEcomStoreMarkup): void {
    const modalRef = this.modalService.open(EcomStoreMarkupDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreMarkup = ecomStoreMarkup;
  }
}
