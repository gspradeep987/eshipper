import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICarrier } from 'app/shared/model/carrier.model';

@Component({
  selector: 'jhi-carrier-detail',
  templateUrl: './carrier-detail.component.html'
})
export class CarrierDetailComponent implements OnInit {
  carrier: ICarrier | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrier }) => (this.carrier = carrier));
  }

  previousState(): void {
    window.history.back();
  }
}
