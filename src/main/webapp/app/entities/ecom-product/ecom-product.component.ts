import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from './ecom-product.service';
import { EcomProductDeleteDialogComponent } from './ecom-product-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-product',
  templateUrl: './ecom-product.component.html'
})
export class EcomProductComponent implements OnInit, OnDestroy {
  ecomProducts?: IEcomProduct[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomProductService: EcomProductService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomProductService.query().subscribe((res: HttpResponse<IEcomProduct[]>) => (this.ecomProducts = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomProducts();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomProduct): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomProducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomProductListModification', () => this.loadAll());
  }

  delete(ecomProduct: IEcomProduct): void {
    const modalRef = this.modalService.open(EcomProductDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomProduct = ecomProduct;
  }
}
