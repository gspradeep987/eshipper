import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreSync } from 'app/shared/model/ecom-store-sync.model';
import { EcomStoreSyncService } from './ecom-store-sync.service';
import { EcomStoreSyncDeleteDialogComponent } from './ecom-store-sync-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-sync',
  templateUrl: './ecom-store-sync.component.html'
})
export class EcomStoreSyncComponent implements OnInit, OnDestroy {
  ecomStoreSyncs?: IEcomStoreSync[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreSyncService: EcomStoreSyncService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreSyncService.query().subscribe((res: HttpResponse<IEcomStoreSync[]>) => (this.ecomStoreSyncs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreSyncs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreSync): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreSyncs(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreSyncListModification', () => this.loadAll());
  }

  delete(ecomStoreSync: IEcomStoreSync): void {
    const modalRef = this.modalService.open(EcomStoreSyncDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreSync = ecomStoreSync;
  }
}
