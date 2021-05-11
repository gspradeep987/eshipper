import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomMarkupTertiary } from '../ecom-markup-tertiary.model';

@Component({
  selector: 'jhi-ecom-markup-tertiary-detail',
  templateUrl: './ecom-markup-tertiary-detail.component.html',
})
export class EcomMarkupTertiaryDetailComponent implements OnInit {
  ecomMarkupTertiary: IEcomMarkupTertiary | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupTertiary }) => {
      this.ecomMarkupTertiary = ecomMarkupTertiary;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
