import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';
import { EcomOrderPaymentStatusService } from './ecom-order-payment-status.service';
import { EcomOrderPaymentStatusDeleteDialogComponent } from './ecom-order-payment-status-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order-payment-status',
  templateUrl: './ecom-order-payment-status.component.html',
})
export class EcomOrderPaymentStatusComponent implements OnInit, OnDestroy {
  ecomOrderPaymentStatuses?: IEcomOrderPaymentStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomOrderPaymentStatusService: EcomOrderPaymentStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomOrderPaymentStatusService
      .query()
      .subscribe((res: HttpResponse<IEcomOrderPaymentStatus[]>) => (this.ecomOrderPaymentStatuses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomOrderPaymentStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomOrderPaymentStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomOrderPaymentStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomOrderPaymentStatusListModification', () => this.loadAll());
  }

  delete(ecomOrderPaymentStatus: IEcomOrderPaymentStatus): void {
    const modalRef = this.modalService.open(EcomOrderPaymentStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrderPaymentStatus = ecomOrderPaymentStatus;
  }
}
