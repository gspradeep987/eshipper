import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

@Component({
  selector: 'jhi-ecom-mail-template-detail',
  templateUrl: './ecom-mail-template-detail.component.html'
})
export class EcomMailTemplateDetailComponent implements OnInit {
  ecomMailTemplate: IEcomMailTemplate | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomMailTemplate }) => (this.ecomMailTemplate = ecomMailTemplate));
  }

  previousState(): void {
    window.history.back();
  }
}
