import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from './ecom-order.service';
import { EcomOrderDeleteDialogComponent } from './ecom-order-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order',
  templateUrl: './ecom-order.component.html'
})
export class EcomOrderComponent implements OnInit, OnDestroy {
  ecomOrders?: IEcomOrder[];
  eventSubscriber?: Subscription;

  constructor(protected ecomOrderService: EcomOrderService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.ecomOrderService.query().subscribe((res: HttpResponse<IEcomOrder[]>) => (this.ecomOrders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomOrders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomOrder): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomOrders(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomOrderListModification', () => this.loadAll());
  }

  delete(ecomOrder: IEcomOrder): void {
    const modalRef = this.modalService.open(EcomOrderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrder = ecomOrder;
  }
}
