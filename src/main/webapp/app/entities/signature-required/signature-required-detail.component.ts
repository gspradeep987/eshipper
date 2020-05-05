import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISignatureRequired } from 'app/shared/model/signature-required.model';

@Component({
  selector: 'jhi-signature-required-detail',
  templateUrl: './signature-required-detail.component.html'
})
export class SignatureRequiredDetailComponent implements OnInit {
  signatureRequired: ISignatureRequired | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ signatureRequired }) => (this.signatureRequired = signatureRequired));
  }

  previousState(): void {
    window.history.back();
  }
}
