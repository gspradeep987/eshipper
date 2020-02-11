import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from './ecom-store.service';
import { EcomStoreDeleteDialogComponent } from './ecom-store-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store',
  templateUrl: './ecom-store.component.html'
})
export class EcomStoreComponent implements OnInit, OnDestroy {
  ecomStores?: IEcomStore[];
  eventSubscriber?: Subscription;

  constructor(protected ecomStoreService: EcomStoreService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.ecomStoreService.query().subscribe((res: HttpResponse<IEcomStore[]>) => (this.ecomStores = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStores();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStore): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStores(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreListModification', () => this.loadAll());
  }

  delete(ecomStore: IEcomStore): void {
    const modalRef = this.modalService.open(EcomStoreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStore = ecomStore;
  }
}
