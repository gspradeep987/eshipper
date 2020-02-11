import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';

@Component({
  selector: 'jhi-ecom-markup-quaternary-detail',
  templateUrl: './ecom-markup-quaternary-detail.component.html'
})
export class EcomMarkupQuaternaryDetailComponent implements OnInit {
  ecomMarkupQuaternary: IEcomMarkupQuaternary | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupQuaternary }) => (this.ecomMarkupQuaternary = ecomMarkupQuaternary));
  }

  previousState(): void {
    window.history.back();
  }
}
