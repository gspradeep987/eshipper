import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreSync } from '../ecom-store-sync.model';
import { EcomStoreSyncService } from '../service/ecom-store-sync.service';
import { EcomStoreSyncDeleteDialogComponent } from '../delete/ecom-store-sync-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-sync',
  templateUrl: './ecom-store-sync.component.html',
})
export class EcomStoreSyncComponent implements OnInit {
  ecomStoreSyncs?: IEcomStoreSync[];
  isLoading = false;

  constructor(protected ecomStoreSyncService: EcomStoreSyncService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreSyncService.query().subscribe(
      (res: HttpResponse<IEcomStoreSync[]>) => {
        this.isLoading = false;
        this.ecomStoreSyncs = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStoreSync): number {
    return item.id!;
  }

  delete(ecomStoreSync: IEcomStoreSync): void {
    const modalRef = this.modalService.open(EcomStoreSyncDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreSync = ecomStoreSync;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
