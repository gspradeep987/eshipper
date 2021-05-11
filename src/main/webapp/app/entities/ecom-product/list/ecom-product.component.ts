import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProduct } from '../ecom-product.model';
import { EcomProductService } from '../service/ecom-product.service';
import { EcomProductDeleteDialogComponent } from '../delete/ecom-product-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-product',
  templateUrl: './ecom-product.component.html',
})
export class EcomProductComponent implements OnInit {
  ecomProducts?: IEcomProduct[];
  isLoading = false;

  constructor(protected ecomProductService: EcomProductService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomProductService.query().subscribe(
      (res: HttpResponse<IEcomProduct[]>) => {
        this.isLoading = false;
        this.ecomProducts = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomProduct): number {
    return item.id!;
  }

  delete(ecomProduct: IEcomProduct): void {
    const modalRef = this.modalService.open(EcomProductDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomProduct = ecomProduct;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
