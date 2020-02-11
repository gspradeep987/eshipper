import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IShipmentService } from 'app/shared/model/shipment-service.model';

@Component({
  selector: 'jhi-shipment-service-detail',
  templateUrl: './shipment-service-detail.component.html'
})
export class ShipmentServiceDetailComponent implements OnInit {
  shipmentService: IShipmentService | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shipmentService }) => (this.shipmentService = shipmentService));
  }

  previousState(): void {
    window.history.back();
  }
}
