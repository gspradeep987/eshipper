import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShipmentService } from '../shipment-service.model';
import { ShipmentServiceService } from '../service/shipment-service.service';
import { ShipmentServiceDeleteDialogComponent } from '../delete/shipment-service-delete-dialog.component';

@Component({
  selector: 'jhi-shipment-service',
  templateUrl: './shipment-service.component.html',
})
export class ShipmentServiceComponent implements OnInit {
  shipmentServices?: IShipmentService[];
  isLoading = false;

  constructor(protected shipmentServiceService: ShipmentServiceService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.shipmentServiceService.query().subscribe(
      (res: HttpResponse<IShipmentService[]>) => {
        this.isLoading = false;
        this.shipmentServices = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IShipmentService): number {
    return item.id!;
  }

  delete(shipmentService: IShipmentService): void {
    const modalRef = this.modalService.open(ShipmentServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shipmentService = shipmentService;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
