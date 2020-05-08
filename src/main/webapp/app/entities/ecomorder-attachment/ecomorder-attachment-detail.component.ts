import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';

@Component({
  selector: 'jhi-ecomorder-attachment-detail',
  templateUrl: './ecomorder-attachment-detail.component.html'
})
export class EcomorderAttachmentDetailComponent implements OnInit {
  ecomorderAttachment: IEcomorderAttachment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomorderAttachment }) => (this.ecomorderAttachment = ecomorderAttachment));
  }

  previousState(): void {
    window.history.back();
  }
}
