import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICarrierService } from 'app/shared/model/carrier-service.model';

@Component({
  selector: 'jhi-carrier-service-detail',
  templateUrl: './carrier-service-detail.component.html'
})
export class CarrierServiceDetailComponent implements OnInit {
  carrierService: ICarrierService | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrierService }) => (this.carrierService = carrierService));
  }

  previousState(): void {
    window.history.back();
  }
}
