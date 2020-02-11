import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

@Component({
  selector: 'jhi-ecom-store-package-settings-detail',
  templateUrl: './ecom-store-package-settings-detail.component.html'
})
export class EcomStorePackageSettingsDetailComponent implements OnInit {
  ecomStorePackageSettings: IEcomStorePackageSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStorePackageSettings }) => (this.ecomStorePackageSettings = ecomStorePackageSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
