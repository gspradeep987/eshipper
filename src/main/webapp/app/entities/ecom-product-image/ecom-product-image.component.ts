import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProductImage } from 'app/shared/model/ecom-product-image.model';
import { EcomProductImageService } from './ecom-product-image.service';
import { EcomProductImageDeleteDialogComponent } from './ecom-product-image-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-product-image',
  templateUrl: './ecom-product-image.component.html'
})
export class EcomProductImageComponent implements OnInit, OnDestroy {
  ecomProductImages?: IEcomProductImage[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomProductImageService: EcomProductImageService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomProductImageService.query().subscribe((res: HttpResponse<IEcomProductImage[]>) => (this.ecomProductImages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomProductImages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomProductImage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomProductImages(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomProductImageListModification', () => this.loadAll());
  }

  delete(ecomProductImage: IEcomProductImage): void {
    const modalRef = this.modalService.open(EcomProductImageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomProductImage = ecomProductImage;
  }
}
