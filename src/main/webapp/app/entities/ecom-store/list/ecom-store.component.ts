import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStore } from '../ecom-store.model';
import { EcomStoreService } from '../service/ecom-store.service';
import { EcomStoreDeleteDialogComponent } from '../delete/ecom-store-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store',
  templateUrl: './ecom-store.component.html',
})
export class EcomStoreComponent implements OnInit {
  ecomStores?: IEcomStore[];
  isLoading = false;

  constructor(protected ecomStoreService: EcomStoreService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreService.query().subscribe(
      (res: HttpResponse<IEcomStore[]>) => {
        this.isLoading = false;
        this.ecomStores = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStore): number {
    return item.id!;
  }

  delete(ecomStore: IEcomStore): void {
    const modalRef = this.modalService.open(EcomStoreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStore = ecomStore;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
