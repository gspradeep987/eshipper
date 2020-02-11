import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IShipmentService } from 'app/shared/model/shipment-service.model';
import { ShipmentServiceService } from './shipment-service.service';

@Component({
  templateUrl: './shipment-service-delete-dialog.component.html'
})
export class ShipmentServiceDeleteDialogComponent {
  shipmentService?: IShipmentService;

  constructor(
    protected shipmentServiceService: ShipmentServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shipmentServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('shipmentServiceListModification');
      this.activeModal.close();
    });
  }
}
