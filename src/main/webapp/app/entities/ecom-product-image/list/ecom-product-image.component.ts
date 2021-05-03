import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProductImage } from '../ecom-product-image.model';
import { EcomProductImageService } from '../service/ecom-product-image.service';
import { EcomProductImageDeleteDialogComponent } from '../delete/ecom-product-image-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-product-image',
  templateUrl: './ecom-product-image.component.html',
})
export class EcomProductImageComponent implements OnInit {
  ecomProductImages?: IEcomProductImage[];
  isLoading = false;

  constructor(protected ecomProductImageService: EcomProductImageService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomProductImageService.query().subscribe(
      (res: HttpResponse<IEcomProductImage[]>) => {
        this.isLoading = false;
        this.ecomProductImages = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomProductImage): number {
    return item.id!;
  }

  delete(ecomProductImage: IEcomProductImage): void {
    const modalRef = this.modalService.open(EcomProductImageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomProductImage = ecomProductImage;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
