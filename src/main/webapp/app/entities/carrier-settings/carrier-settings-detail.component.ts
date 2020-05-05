import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';

@Component({
  selector: 'jhi-carrier-settings-detail',
  templateUrl: './carrier-settings-detail.component.html'
})
export class CarrierSettingsDetailComponent implements OnInit {
  carrierSettings: ICarrierSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carrierSettings }) => (this.carrierSettings = carrierSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
