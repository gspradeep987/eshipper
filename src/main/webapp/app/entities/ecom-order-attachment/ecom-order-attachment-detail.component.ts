import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';

@Component({
  selector: 'jhi-ecom-order-attachment-detail',
  templateUrl: './ecom-order-attachment-detail.component.html'
})
export class EcomOrderAttachmentDetailComponent implements OnInit {
  ecomOrderAttachment: IEcomOrderAttachment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderAttachment }) => (this.ecomOrderAttachment = ecomOrderAttachment));
  }

  previousState(): void {
    window.history.back();
  }
}
