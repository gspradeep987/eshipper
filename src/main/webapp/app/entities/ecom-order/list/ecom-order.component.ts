import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomOrder } from '../ecom-order.model';
import { EcomOrderService } from '../service/ecom-order.service';
import { EcomOrderDeleteDialogComponent } from '../delete/ecom-order-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-order',
  templateUrl: './ecom-order.component.html',
})
export class EcomOrderComponent implements OnInit {
  ecomOrders?: IEcomOrder[];
  isLoading = false;

  constructor(protected ecomOrderService: EcomOrderService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomOrderService.query().subscribe(
      (res: HttpResponse<IEcomOrder[]>) => {
        this.isLoading = false;
        this.ecomOrders = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomOrder): number {
    return item.id!;
  }

  delete(ecomOrder: IEcomOrder): void {
    const modalRef = this.modalService.open(EcomOrderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomOrder = ecomOrder;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
