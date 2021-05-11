import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomMarkupSecondary } from '../ecom-markup-secondary.model';

@Component({
  selector: 'jhi-ecom-markup-secondary-detail',
  templateUrl: './ecom-markup-secondary-detail.component.html',
})
export class EcomMarkupSecondaryDetailComponent implements OnInit {
  ecomMarkupSecondary: IEcomMarkupSecondary | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupSecondary }) => {
      this.ecomMarkupSecondary = ecomMarkupSecondary;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
