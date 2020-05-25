import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';
import { EcomOrderFullfillmentStatusService } from './ecom-order-fullfillment-status.service';
import { EcomOrderFullfillmentStatusDeleteDialogComponent } from './ecom-order-fullfillment-status-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order-fullfillment-status',
  templateUrl: './ecom-order-fullfillment-status.component.html',
})
export class EcomOrderFullfillmentStatusComponent implements OnInit, OnDestroy {
  ecomOrderFullfillmentStatuses?: IEcomOrderFullfillmentStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomOrderFullfillmentStatusService: EcomOrderFullfillmentStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomOrderFullfillmentStatusService
      .query()
      .subscribe((res: HttpResponse<IEcomOrderFullfillmentStatus[]>) => (this.ecomOrderFullfillmentStatuses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomOrderFullfillmentStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomOrderFullfillmentStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomOrderFullfillmentStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomOrderFullfillmentStatusListModification', () => this.loadAll());
  }

  delete(ecomOrderFullfillmentStatus: IEcomOrderFullfillmentStatus): void {
    const modalRef = this.modalService.open(EcomOrderFullfillmentStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrderFullfillmentStatus = ecomOrderFullfillmentStatus;
  }
}
