import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreMarkup } from '../ecom-store-markup.model';
import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';
import { EcomStoreMarkupDeleteDialogComponent } from '../delete/ecom-store-markup-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-markup',
  templateUrl: './ecom-store-markup.component.html',
})
export class EcomStoreMarkupComponent implements OnInit {
  ecomStoreMarkups?: IEcomStoreMarkup[];
  isLoading = false;

  constructor(protected ecomStoreMarkupService: EcomStoreMarkupService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreMarkupService.query().subscribe(
      (res: HttpResponse<IEcomStoreMarkup[]>) => {
        this.isLoading = false;
        this.ecomStoreMarkups = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStoreMarkup): number {
    return item.id!;
  }

  delete(ecomStoreMarkup: IEcomStoreMarkup): void {
    const modalRef = this.modalService.open(EcomStoreMarkupDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreMarkup = ecomStoreMarkup;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
