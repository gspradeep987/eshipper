import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomWarehouse } from '../ecom-warehouse.model';
import { EcomWarehouseService } from '../service/ecom-warehouse.service';
import { EcomWarehouseDeleteDialogComponent } from '../delete/ecom-warehouse-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-warehouse',
  templateUrl: './ecom-warehouse.component.html',
})
export class EcomWarehouseComponent implements OnInit {
  ecomWarehouses?: IEcomWarehouse[];
  isLoading = false;

  constructor(protected ecomWarehouseService: EcomWarehouseService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomWarehouseService.query().subscribe(
      (res: HttpResponse<IEcomWarehouse[]>) => {
        this.isLoading = false;
        this.ecomWarehouses = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomWarehouse): number {
    return item.id!;
  }

  delete(ecomWarehouse: IEcomWarehouse): void {
    const modalRef = this.modalService.open(EcomWarehouseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomWarehouse = ecomWarehouse;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
