import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

@Component({
  selector: 'jhi-ecom-markup-primary-detail',
  templateUrl: './ecom-markup-primary-detail.component.html'
})
export class EcomMarkupPrimaryDetailComponent implements OnInit {
  ecomMarkupPrimary: IEcomMarkupPrimary | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMarkupPrimary }) => (this.ecomMarkupPrimary = ecomMarkupPrimary));
  }

  previousState(): void {
    window.history.back();
  }
}
