import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IShipmentService } from '../shipment-service.model';
import { ShipmentServiceService } from '../service/shipment-service.service';

@Component({
  templateUrl: './shipment-service-delete-dialog.component.html',
})
export class ShipmentServiceDeleteDialogComponent {
  shipmentService?: IShipmentService;

  constructor(protected shipmentServiceService: ShipmentServiceService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.shipmentServiceService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
