import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreImages } from 'app/shared/model/ecom-store-images.model';
import { EcomStoreImagesService } from './ecom-store-images.service';
import { EcomStoreImagesDeleteDialogComponent } from './ecom-store-images-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-images',
  templateUrl: './ecom-store-images.component.html',
})
export class EcomStoreImagesComponent implements OnInit, OnDestroy {
  ecomStoreImages?: IEcomStoreImages[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomStoreImagesService: EcomStoreImagesService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomStoreImagesService.query().subscribe((res: HttpResponse<IEcomStoreImages[]>) => (this.ecomStoreImages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomStoreImages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomStoreImages): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomStoreImages(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomStoreImagesListModification', () => this.loadAll());
  }

  delete(ecomStoreImages: IEcomStoreImages): void {
    const modalRef = this.modalService.open(EcomStoreImagesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreImages = ecomStoreImages;
  }
}
