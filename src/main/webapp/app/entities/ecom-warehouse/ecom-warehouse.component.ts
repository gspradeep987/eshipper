import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';
import { EcomWarehouseService } from './ecom-warehouse.service';
import { EcomWarehouseDeleteDialogComponent } from './ecom-warehouse-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-warehouse',
  templateUrl: './ecom-warehouse.component.html'
})
export class EcomWarehouseComponent implements OnInit, OnDestroy {
  ecomWarehouses?: IEcomWarehouse[];
  eventSubscriber?: Subscription;

  constructor(
    protected ecomWarehouseService: EcomWarehouseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.ecomWarehouseService.query().subscribe((res: HttpResponse<IEcomWarehouse[]>) => (this.ecomWarehouses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEcomWarehouses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEcomWarehouse): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEcomWarehouses(): void {
    this.eventSubscriber = this.eventManager.subscribe('ecomWarehouseListModification', () => this.loadAll());
  }

  delete(ecomWarehouse: IEcomWarehouse): void {
    const modalRef = this.modalService.open(EcomWarehouseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomWarehouse = ecomWarehouse;
  }
}
