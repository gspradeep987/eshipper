import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomStoreColorTheme } from '../ecom-store-color-theme.model';

@Component({
  selector: 'jhi-ecom-store-color-theme-detail',
  templateUrl: './ecom-store-color-theme-detail.component.html',
})
export class EcomStoreColorThemeDetailComponent implements OnInit {
  ecomStoreColorTheme: IEcomStoreColorTheme | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreColorTheme }) => {
      this.ecomStoreColorTheme = ecomStoreColorTheme;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
